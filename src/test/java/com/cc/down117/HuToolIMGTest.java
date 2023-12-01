package com.cc.down117;

import cn.hutool.core.img.FontUtil;
import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HuToolIMGTest {
    @Test
    public void test3e(){
//        ImgUtil.pressText(FileUtil.file("C:\\Users\\CCIMT\\Desktop\\aron_ch_220493526_123664723294491_6285589606242226663_n.jpg"),
//                FileUtil.file("C:\\Users\\CCIMT\\Desktop\\仅供郑航学士学位申请试用.jpg"),
//                "仅供郑航学士学位申请试用", Color.red, FontUtil.createSansSerifFont(40), 10, -10,(float)1 );

        ImgUtil.write(
                Img.from(FileUtil.file("C:\\Users\\CCIMT\\Desktop\\03.jpg"))
                        .pressTextFull("    仅供郑航学士学位申请使用    ", Color.darkGray, FontUtil.createSansSerifFont(60), 10, -20, (float) 0.7).getImg()
                , FileUtil.file("C:\\Users\\CCIMT\\Desktop\\申请使用3.jpg"));

    }

    @Test
    public void test3(){
        int[] myArray = new int[]{99};
        System.out.println(myArray.length);

    }
   @Test
    public void test33(){
       String str = "com.jd.bk.saf.exception.SafJosException:参数错误.商详包含敏感词['最大','乱伦']!#3c45d8b657f944f8adf60b70c8d71ea8(Solution reference: https://jos.jd.com/commondoc?listId=171)";

       Pattern pattern = Pattern.compile("\\['(.*?)'\\]");
       Matcher matcher = pattern.matcher(str);

       while (matcher.find()) {
           String match = matcher.group(1);
           System.out.println("找到了方括号中的字符串>>" + match);

           String match0 = matcher.group(0);
           System.out.println("找到了方括号中的字符串>>" + match0);
       }

       System.out.println("ReUtil.getGroup0找到了方括号中的字符串>>" +  ReUtil.getGroup0("\\['(.*?)'\\]",str));
       System.out.println("ReUtil.getGroup1找到了方括号中的字符串>>" +  ReUtil.getGroup1("\\['(.*?)'\\]",str));

       System.out.println(str.replaceAll(".", "*"));


       String[] starray = new String[]{"卖淫","乱伦"};
       System.out.println(Arrays.toString(starray));

   }



    @Test
    public void test2(){
        Image img1 = new Image("img1", "10", 10);
        Image img2 = new Image("img2", "20", 20);
        Image img4 = new Image("img4", "40", 40);
        List<Image> list = new ArrayList<>();

        list.add(img1);
        list.add(img2);
        list.add(img4);
        System.out.println(list);

        List<Image> list2 = new ArrayList<>();
        list.forEach(e -> {
            e.setImgUrl("copy1");
            list2.add(e);
        });



        System.out.println(list);
        System.out.println(list2);

        List<Image> list3 = new ArrayList<>();




    }



}
