package com.rest.study.image.freeImage.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FreeStringUtils {

    public static String getRenameFilename(String originName) {

        String ext = "";
        int dotIndex = originName.lastIndexOf(".");
        if(dotIndex > -1)
            ext = originName.substring(dotIndex);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
        DecimalFormat df = new DecimalFormat("000");
        return sdf.format(new Date()) + df.format(Math.random() * 1000) + ext;

    }
}
