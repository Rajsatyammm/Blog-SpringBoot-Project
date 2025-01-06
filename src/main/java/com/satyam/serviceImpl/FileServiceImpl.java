package com.satyam.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.satyam.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {

    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
        String randomID = UUID.randomUUID().toString();
        String filePath = path + File.separator + randomID;
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
        return "File uploaded";
    }

}