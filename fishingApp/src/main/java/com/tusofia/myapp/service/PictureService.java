package com.tusofia.myapp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {


    String getFishPicUploadPath(MultipartFile pic) throws IOException;

    String getWaterPicUploadPath(MultipartFile pic) throws IOException;

    String getJournalPicUploadPath(MultipartFile pic) throws IOException;


}
