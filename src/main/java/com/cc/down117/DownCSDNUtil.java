package com.cc.down117;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 爬csdn博客工具
 * create by zhangtao
 */
public class DownCSDNUtil {
    public static void main(String[] args) {

        //        爬所有
        // climb("qq_45774645");
//        爬单个
       // climbOne("unique_perfect","109380996");
        climbDetailByUrl("https://blog.csdn.net/qq_27022241/article/details/108405545", "WIN10环境Maven的安装与配置并修改.m2下setting配置文件");
    }

    public static void climb(String userName) {
        // 把下面这个base_url换成你csdn的地址
        String baseUrl = "https://blog.csdn.net/" + userName + "/";
        String secondUrl = baseUrl + "article/list/";
        // 创建文件夹
        File file = new File("./_posts/");
        if (!file.exists()) {
            file.mkdir();
        }
        for (int i = 1; ; i++) {
            // 从第一页开始爬取
            String startUrl = secondUrl + i;
            Document doc = null;
            try {
                doc = Jsoup.connect(startUrl).get();
            } catch (IOException e) {
                System.out.println("jsoup获取url失败" + e.getMessage());
            }
            Element element = doc.body();
            //找到div class='article-list'
            element = element.select("div.article-list").first();
            if (element == null) {
                break;
            }
            Elements elements = element.children();
            for (Element e : elements) {
                // 拿到文章id
                String articleId = e.attr("data-articleid");
                // 爬取单篇文章
                climbDetailById(baseUrl, articleId);
            }
        }
    }

    private static void climbOne(String userName,String articleId) {
        System.out.println("》》》》》》》爬虫开始《《《《《《《");
        // 把下面这个base_url换成你csdn的地址
        String baseUrl = "https://blog.csdn.net/" + userName + "/";
        String secondUrl = baseUrl + "article/list/";
        // 创建文件夹
        File file = new File("./_posts/");
        if (!file.exists()) {
            file.mkdir();
        }
        System.out.println(articleId);
        // 爬取单篇文章
        climbDetailById(baseUrl, articleId);
        System.out.println("》》》》》》》爬虫结束《《《《《《《");
    }


    public static void climbDetailByUrls(String... urls){

        for (String url : urls) {
            climbDetailByUrl(url);
        }
    }


    public static void climbDetailByUrl(String csdnUrl) {
        climbDetailByUrl(csdnUrl,null);
    }


    public static void climbDetailByUrl(String csdnUrl,String fileName) {
        File file = new File("./_posts/");
        if (!file.exists()) {
            file.mkdir();
        }
        String startUrl = csdnUrl;
        Document doc = null;
        try {
            doc = Jsoup.connect(startUrl).get();
        } catch (IOException e) {
            System.out.println("jsoup获取url失败" + e.getMessage());
        }
        Element element = doc.body();
        Element htmlElement = element.select("div#content_views").first();
        Element titleElement = element.selectFirst(".title-article");
        if (StrUtil.isBlank(fileName)) {
            fileName = titleElement.text();
        }


        System.out.println(fileName);

        ThreadLocalUtil.set(fileName);
        // 设置jekyll格式博客title
        String jekyllTitle = "title:   " + fileName + "\n";

        // 设置jekyll格式博客categories
        Elements elements = element.select("div.tags-box");
        String jekyllCategories = "";
        if (elements.size() > 1) {
            jekyllCategories = "categories:\n";
            jekyllCategories = getTagsBoxValue(elements, 1, jekyllCategories);
        }

        // 设置jekyll格式博客tags
        String jekyllTags = "tags:\n";
        jekyllTags = getTagsBoxValue(elements, 0, jekyllTags);

        // 获取时间
        Element timeElement = element.selectFirst("span.time");
        String time = timeElement.text().substring(5);
        System.out.println(time);

        // 设置jekyll格式博客date
        String jekyllDate = "date:   " + time + "\n";
        String md = Html2MD.getMarkDownText(htmlElement);
        // String md = HtmlToMd.getTextContent(htmlElement); 转出来的效果不满意，弃用
        md =  "> 转载："+csdnUrl+"".concat(md);
        System.out.println(md);

        String jekylltr = "---\n" + "layout:  post\n" + jekyllTitle + jekyllDate
                + "author:  'zhangtao'\nheader-img: 'img/post-bg-2015.jpg'\ncatalog:   false\n"
                + jekyllCategories + jekyllTags + "\n---\n";
        String date = time.split(" ")[0];
        String mdFileName = "./_posts/" + fileName + ".md";

        FileUtil.writeString(md,FileUtil.touch( mdFileName), CharsetUtil.UTF_8);


    }
    private static void climbDetailById(String baseUrl, String articleId) {
        String startUrl = baseUrl + "article/details/" + articleId;
        Document doc = null;
        try {
            doc = Jsoup.connect(startUrl).get();
        } catch (IOException e) {
            System.out.println("jsoup获取url失败" + e.getMessage());
        }
        Element element = doc.body();
        Element htmlElement = element.select("div#content_views").first();
        Element titleElement = element.selectFirst(".title-article");
        String fileName = titleElement.text();
        System.out.println(fileName);
        // 设置jekyll格式博客title
        String jekyllTitle = "title:   " + fileName + "\n";

        // 设置jekyll格式博客categories
        Elements elements = element.select("div.tags-box");
        String jekyllCategories = "";
        if (elements.size() > 1) {
            jekyllCategories = "categories:\n";
            jekyllCategories = getTagsBoxValue(elements, 1, jekyllCategories);
        }

        // 设置jekyll格式博客tags
        String jekyllTags = "tags:\n";
        jekyllTags = getTagsBoxValue(elements, 0, jekyllTags);

        // 获取时间
        Element timeElement = element.selectFirst("span.time");
        String time = timeElement.text().substring(5);
        System.out.println(time);

        // 设置jekyll格式博客date
        String jekyllDate = "date:   " + time + "\n";
        String md = Html2MD.getMarkDownText(htmlElement);
        // String md = HtmlToMd.getTextContent(htmlElement); 转出来的效果不满意，弃用

        System.out.println(md);

        String jekylltr = "---\n" + "layout:  post\n" + jekyllTitle + jekyllDate
                + "author:  'zhangtao'\nheader-img: 'img/post-bg-2015.jpg'\ncatalog:   false\n"
                + jekyllCategories + jekyllTags + "\n---\n";
        String date = time.split(" ")[0];
       //  String mdFileName = "./_posts/" + date + '-' + fileName + ".markdown";
        String mdFileName = fileName + ".md";
        md = jekylltr + md;
        FileWriter writer;
        try {
            writer = new FileWriter(mdFileName);
            writer.write(md);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getTagsBoxValue(Elements elements, int index, String jekyllCategories) {
        Elements categories = elements.get(index).select("a.tag-link");
        for (Element e : categories) {
            String temp = e.text().replace("\t", "").replace("\n", "").replace("\r", "");
            jekyllCategories += "-" + temp + "\n";
        }
        return jekyllCategories;
    }


}
