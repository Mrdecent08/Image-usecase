package com.example.demo.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.serviceImpl.ImageServiceImpl;

public class ImageServiceTests {

	private ImageRepository imageRepository;
	private ImageServiceImpl imageService;
	Image image;
	MockMultipartFile file = new MockMultipartFile("file", "hello.txt",  MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes()
    );
	@BeforeEach
	public void setup() {
		imageRepository = Mockito.mock(ImageRepository.class);
		imageService = new ImageServiceImpl(imageRepository);
		image = new Image("1","Image 1", "Image 1 Description", "0".getBytes(), 1);
	}

	@DisplayName("JUnit test for saveImage method")
	@Test
	public void givenImageObject_whenSaveImage_thenReturnImageObject() throws IOException {
		// given - precondition or setup

		given(imageRepository.save(image)).willReturn(image);

		String savedImage = imageService.saveImage(image.getId(),file, image.getName(), image.getDescription(), image.getUserId());

		assertThat(savedImage).isNotNull();
	}

	
	@DisplayName("JUnit test for getAllImages method")
	@Test
	public void givenImagesList_whenGetAllImages_thenReturnImagesList() {
		// given - precondition or setup

		Image image1 = new Image("1","Image 1", "Image 1 Description", "0".getBytes(), 1);

		given(imageRepository.findAll()).willReturn(List.of(image,image1));

		List<Image> ImageList = imageService.getAllImages();

		assertThat(ImageList).isNotNull();
		assertThat(ImageList.size()).isEqualTo(2);
	}

	

	@DisplayName("JUnit test for getImageById method")
	@Test
	public void givenImageId_whenGetImageById_thenReturnImageObject() {
		
		given(imageRepository.findById("1")).willReturn(Optional.of(image));

		List<Image> savedImage = imageService.getAllImagesByUserId((long) 1);

		assertThat(savedImage).isNotNull();

	}


	@DisplayName("JUnit test for deleteImage method")
	@Test
	public void givenImageId_whenDeleteImage_thenNothing() {
		String ImageId = "1";

		willDoNothing().given(imageRepository).deleteById(ImageId);

		imageService.deleteImage(ImageId);

		verify(imageRepository, times(1)).deleteById(ImageId);
	}

}
