package com.cc.down117;

import cn.hutool.core.img.FontUtil;
import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class HuToolIMGTest {
    @Test
    public void test3e(){
//        ImgUtil.pressText(FileUtil.file("C:\\Users\\CCIMT\\Desktop\\aron_ch_220493526_123664723294491_6285589606242226663_n.jpg"),
//                FileUtil.file("C:\\Users\\CCIMT\\Desktop\\仅供郑航学士学位申请试用.jpg"),
//                "仅供郑航学士学位申请试用", Color.red, FontUtil.createSansSerifFont(40), 10, -10,(float)1 );

        ImgUtil.write(
                Img.from(FileUtil.file("C:\\Users\\CCIMT\\Desktop\\03.jpg"))
                        .pressTextFull("    仅供郑航学士学位申请使用    ", Color.darkGray, FontUtil.createSansSerifFont(50), 10, -20, (float) 0.7).getImg()
                , FileUtil.file("C:\\Users\\CCIMT\\Desktop\\申请使用3.jpg"));

    }

}
