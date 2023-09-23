package com.cc.down117;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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



    @Test
    void contextLoads() throws InterruptedException {
        long l = System.currentTimeMillis();

        Thread.sleep(1000);
        System.out.println((System.currentTimeMillis() - l));
    }


}
