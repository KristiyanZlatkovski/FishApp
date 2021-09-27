package com.tusofia.myapp.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tusofia.myapp.web.FileUploadUtil;
@Service
public class PictureServiceImpl implements PictureService {

	@Override
	public String getJournalPicUploadPath(MultipartFile pic) throws IOException {
		String resultPath="";
		
		if(pic.isEmpty()) {
			resultPath="/images/fish/defaultFish.png";
			
		}
		else {
			String extension=pic.getOriginalFilename().split("\\.")[1];	
		       DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		       String fileName="logPic"+df.format(new Date())+"."+extension;
		       String uploadDir="/uploads/pictures/journalPictures";
		       FileUploadUtil.saveFile(".."+uploadDir, fileName, pic);
			
		       
		       resultPath= uploadDir+"/"+fileName;
			
		}
		
		
		return resultPath;
	}

	@Override
	public String getWaterPicUploadPath(MultipartFile pic) throws IOException {
	String resultPath="";
		
		if(pic.isEmpty()) {
			resultPath="/images/water/defaultWater.png";
			
		}
		else {
			String extension=pic.getOriginalFilename().split("\\.")[1];	
		       DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		       String fileName="logPic"+df.format(new Date())+"."+extension;
		       String uploadDir="/uploads/pictures/waterPropPictures";
		       FileUploadUtil.saveFile(".."+uploadDir, fileName, pic);
			
		       
		       resultPath= uploadDir+"/"+fileName;
			
		}
		
		
		return resultPath;
	}

	@Override
	public String getFishPicUploadPath(MultipartFile pic) throws IOException {
String resultPath="";
		
		if(pic.isEmpty()) {
			resultPath="/images/fish/defaultFish.png";
			
		}
		else {
			String extension=pic.getOriginalFilename().split("\\.")[1];	
		       DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		       String fileName="logPic"+df.format(new Date())+"."+extension;
		       String uploadDir="/uploads/pictures/fishPropPictures";
		       FileUploadUtil.saveFile(".."+uploadDir, fileName, pic);
			
		       
		       resultPath= uploadDir+"/"+fileName;
			
		}
		
		
		return resultPath;
	}
	
	
	

}
