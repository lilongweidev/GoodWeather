package com.llw.mvplibrary.bean;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class WallPaper extends LitePalSupport {

    private String ImgUrl;

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }
}
