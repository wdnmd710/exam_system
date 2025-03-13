package com.project.template.dto;
//修改位置
//
//
//!!!!!

//???????
import lombok.Data;

import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class FileInfoDTO {

    private String server;
    private String key;
    private String bucket;
    private String alt;

    public String getUrl() {
        return server + "/" + bucket + key;
    }

    public String buildFilePath(String basePath) {
        return Paths.get(basePath, bucket, key).toString();
    }


}
