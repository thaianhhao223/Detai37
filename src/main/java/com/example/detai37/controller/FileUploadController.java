package com.example.detai37.controller;


import com.example.detai37.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Dùng để test chức năng upload file
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/")
    public String postFile(@RequestPart(value = "file") MultipartFile file){
        String url = "";
        url = storageService.store(file);
        return url;
    }
    @PostMapping("/multifile")
    public List<String> postMutiFile(@RequestPart(value = "files") MultipartFile[] file){
        List<String> listUrl = new ArrayList<>();
        String url;
        for(MultipartFile singleFile: file){
            url = "";
            url = storageService.store(singleFile);
            listUrl.add(url);
        }
        return listUrl;
    }
}
