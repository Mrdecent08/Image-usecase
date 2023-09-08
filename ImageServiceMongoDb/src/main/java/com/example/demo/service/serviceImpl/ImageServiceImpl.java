package com.example.demo.service.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.ImageNotFoundException;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import com.example.demo.utils.ImageUtils;

@Service
public class ImageServiceImpl implements ImageService{

	ImageRepository imageRepository;

	public ImageServiceImpl(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}

	public String saveImage(String id, MultipartFile file, String name, String description, Long userId) throws IOException {
		Image image = new Image();
		image.setId(id);
		image.setName(name);
		image.setDescription(description);
		image.setImage(ImageUtils.compressImage(file.getBytes()));
		image.setUserId(userId);
		imageRepository.save(image);
		return String.format("Image saved in DB %s", name);
	}

	public List<Image> getAllImages() {
		return imageRepository.findAll();
	}
	
	public byte[] downloadImg(String imageId) {
		Optional<Image> imgData = imageRepository.findById(imageId);
		if(imgData.isEmpty()) {
			throw new ImageNotFoundException("No Image Found");
		}
		return ImageUtils.decompressImage(imgData.get().getImage());
	}

	public String deleteImage(String imageId) {
		imageRepository.deleteById(imageId);
		return "Deleted Image Successfully";
	}

	public List<Image> getAllImagesByUserId(Long userId) {
		return imageRepository.findAllByUserId(userId);
	}

	public String updateImage(String imageId, MultipartFile file, String name, String description, Long userId) throws IOException {
		Optional<Image> imgData = imageRepository.findById(imageId);
		if(imgData.isEmpty()) {
			throw new ImageNotFoundException("No Image Found");
		}
		else {
			Image img = imgData.get();
			img.setName(name);
			img.setDescription(description);
			img.setImage(ImageUtils.compressImage(file.getBytes()));
			img.setUserId(userId);
			imageRepository.save(img);
			return "Image Updated Successfully";
		}
		
	}

}
