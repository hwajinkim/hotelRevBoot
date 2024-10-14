package com.boot.sonorous.common.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtil {

    public static String thumbnailUpload(MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/thumbnails";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        return fileName;
    }
    public static String fileUpload(MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        return fileName;
    }
}
