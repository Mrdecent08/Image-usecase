package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;

public interface ImageService{

	List<Image> getAllImages();

	String saveImage(String id, MultipartFile file, String name, String description, Long userId) throws IOException;

	byte[] downloadImg(String imageId);

	String deleteImage(String imageId);

	String updateImage(String imageId, MultipartFile file, String name, String description, Long userId) throws IOException;

	List<Image> getAllImagesByUserId(Long userId);

	

}
