package com.rest.study.common.controller;

import javax.persistence.Converter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtils {
    public static String generateUniqueName(String originName) {
        String ext = ""; // 확장자
        int dotIndex = originName.lastIndexOf(".");
        if(dotIndex > -1)
                ext = originName.substring(dotIndex);
        return UUID.randomUUID().toString() + ext;
    }


    public static Timestamp convert(String source) throws IllegalAccessException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(source);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalAccessException("string에서 timestamp로 변환 실패");
        }
    }


}
