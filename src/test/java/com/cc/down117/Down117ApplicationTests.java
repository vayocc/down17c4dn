package com.cc.down117;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @SpringBootTest
class Down117ApplicationTests {

    @Test
    public void teste(){
        String str = """
                nihao
                wobuhao
                """;
        System.out.println(str);
    }

    /**
     * 貌似是取整数的正则
     */
    @Test
    public void test3e(){
        String str = "([1-9][0-9]+|[0-9])";
        String value = "300010000.01yuan";
        String s = ReUtil.getGroup0( str ,value);
        System.out.println(s);
    }


    @Test
    public void test3e2(){

        String value = "300010/000.01yuan";
        String value1 = null;
        String s = ReUtil.replaceAll(value ,  "([./])","($1)");
        System.out.println(s);

        System.out.println(StrUtil.replace(value, "[./]", "-"));

        String s1 = value1.replaceAll("[./]", "-");
        System.out.println(s1);

    }
    @Test
    public void test3e22(){

        String string = "BookIMG2PlatformDTO(serialNo=172430, localFileIds=[172430601], shopId=16467985665, actions=[主图列表, 商品详情], platformItemId=1289)";

        // 提取serialNo的值
        Pattern serialNoPattern = Pattern.compile("serialNo=(.*?)(?:,|\\))"); // "(.*?)" 匹配任意字符（除换行符外）的任意次数，尽可能少地匹配。 // "(?:,|\))" 匹配逗号或右括号。
        Matcher serialNoMatcher = serialNoPattern.matcher(string);
        String serialNo = serialNoMatcher.find() ? serialNoMatcher.group(1) : null;
        // 提取platformItemId的值
        Pattern platformItemIdPattern = Pattern.compile("platformItemId=(.*?)(?:,|\\))");// "(.*?)" 匹配任意字符（除换行符外）的任意次数，尽可能少地匹配。 // "(?:,|\))" 匹配逗号或右括号。
        Matcher platformItemIdMatcher = platformItemIdPattern.matcher(string);
        String platformItemId = platformItemIdMatcher.find() ? platformItemIdMatcher.group(1) : null;

        System.out.println("serialNo: " + serialNo);
        System.out.println("platformItemId: " + platformItemId);


        // 提取serialNo和platformItemId的值
        Pattern pattern = Pattern.compile("(serialNo|platformItemId)=(.*?)(?:,|\\))");// "(.*?)" 匹配任意字符（除换行符外）的任意次数，尽可能少地匹配。 // "(?:,|\))" 匹配逗号或右括号。
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            System.out.println(key + ": " + value);
        }

    }

    @Test
    public void test3er(){
        String str = "p-123123p-";
        String replace = StrUtil.replace(str, "p-","");
        System.out.println(str);
        System.out.println(replace);
    }

    @Test
    public void test3e2123r(){
        String dateString2 = null;
        String dateString1 = "2023.7月";
        String dateString = "2023年7月";

        String regex = "(\\d{4})[年.]?(\\d{1,2})[月.]?";
        Pattern pattern = Pattern.compile(regex);

        String formattedDate2 = formatDate(dateString2, pattern);
        String formattedDate1 = formatDate(dateString1, pattern);
        String formattedDate = formatDate(dateString, pattern);

        System.out.println(formattedDate2);
        System.out.println(formattedDate1);
        System.out.println(formattedDate);


        System.out.println(ReUtil.get(Pattern.compile(regex), dateString2, 2));



    }

    private static String formatDate(String dateString, Pattern pattern) {
        Matcher matcher = pattern.matcher(dateString);
        if (matcher.find()) {
            String year = matcher.group(1);
            String month = matcher.group(2);
            return year + "-" + month;
        }
        return dateString;
    }


    @Test
    public void teste2(){
        int value = 3;

        switch (value) {
            case 1:
                // 处理值为1的情况
                break;
            case 2:
            case 3:
                // 处理值为2或3的情况
                System.out.println(value);
                break;
            default:
                // 处理其他情况
                break;
        }
    }



    @Test
    void contextLoads() throws InterruptedException {
        long l = System.currentTimeMillis();

        Thread.sleep(1000);
        System.out.println((System.currentTimeMillis() - l));
    }


}
