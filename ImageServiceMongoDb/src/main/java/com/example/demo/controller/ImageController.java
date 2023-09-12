package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;

import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/image")
@Tag(
	name = "CRUD Rest APIs on Image DB"
)
public class ImageController {
	
	@Value("${login-url}")
	private String baseUrl;
	Logger logger = LoggerFactory.getLogger(ImageController.class);

	RestTemplate restTemplate = new RestTemplate();

	private ImageService imageService;

	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}

	@Operation(
			summary = "Retrieve Images Rest API"
	)
	@GetMapping("/")
	public List<Image> getAllImages() {
		logger.info("Retrieving All Images from MongoDB");
		if (imageService.getAllImages() == null) {
			logger.warn("Image Table is Empty");
		}
		return imageService.getAllImages();
	}

	@Operation(
			summary = "Adding a Image Rest API"
	)
	@PostMapping("/save")
	@Retry(name = "userServiceDown", fallbackMethod = "userServiceDown")
	public String saveImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("id") String id, @RequestParam("description") String description,
			@RequestParam("userid") Long userId, @RequestParam("username") String username,
			@RequestParam("password") String password) throws IOException {
		boolean response = restTemplate.getForObject(
				baseUrl+"username=" + username + "&password=" + password,
				Boolean.class);
		if (response) {
			logger.info("Saving An Image into MongoDB");
			return imageService.saveImage(id, file, name, description, userId);
		} 
		logger.error("User Authentication Failed !");
		return "User Authentication Failed!";
		
	}

	
	@Operation(
			summary = "Retrieving Image Using ID Rest API"
	)
	@GetMapping("/{imageId}")
	public ResponseEntity<?> downloadImg(@PathVariable String imageId) {
		byte[] imgData = imageService.downloadImg(imageId);
		logger.info("Retrieving of an Image Using Image ID from MongoDB");
		if (imageService.downloadImg(imageId) == null) {
			logger.warn("No Image Found");
			return null;
		}
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imgData);
	}

	@Operation(
			summary = "Deleting a Image Using ID Rest API"
	)
	@DeleteMapping("/{imageId}")
	public String deleteImage(@PathVariable String imageId) {
		logger.info("Deleting an Image Using Image ID from MongoDB");
		return imageService.deleteImage(imageId);
	}

	@Operation(
			summary = "Updating a Image Using Image ID Rest API"
	)
	@PutMapping("/{imageId}")
	public String updateImage(@PathVariable String imageId, @RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("id") String id,
			@RequestParam("description") String description, @RequestParam("userid") Long userId) throws IOException {
		logger.info("Updating an Image Based on Image ID");
		return imageService.updateImage(imageId, file, name, description, userId);
	}
	
	@Operation(
			summary = "Retrieving All Images Of a Customer Using Customer ID Rest API"
	)
	@GetMapping("/user/{userId}")
	public List<Image> getAllImagesByUserId(@PathVariable Long userId) {
		logger.info("Retrieving of Images By User ID");
		return imageService.getAllImagesByUserId(userId);
	}

	public String userServiceDown(Exception ex) {
		logger.warn("User Service is Down!");
		return "User Service Is Down";
	}

}
