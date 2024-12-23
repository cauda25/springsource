package com.example.movie.dto;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private String folderPath;

    // 썸네일 경로
    public String getThumbImageURL() {
        String fullPath = "";
        try {
            fullPath = URLEncoder.encode(folderPath + File.separator + "s_" + uuid + "_" + fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    // 원본 이미지 경로
    public String getImageURL() {
        String fullPath = "";
        try {
            fullPath = URLEncoder.encode(folderPath + File.separator + uuid + "_" + fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }
}
