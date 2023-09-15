package com.cc.down117;

import cn.hutool.core.util.ObjectUtil;


/**
 * @author IT1N0
 * @Description:
 * @date 2022/11/15 13:47
 */
public class ThreadLocalUtil {

    private static ThreadLocal<String> FILE_NAME = new ThreadLocal<>();


    public static void set(String val) {
        ThreadLocalUtil.FILE_NAME.set(val);
    }



    public static String get() {
        return FILE_NAME.get();
    }
    public static void remove() {
        if (ObjectUtil.isNotNull(FILE_NAME))
            FILE_NAME.remove();
    }




}
