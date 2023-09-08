package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Image;

public interface ImageRepository extends MongoRepository<Image,String>{
	
	Optional<Image> findByName(String fileName);

	Optional<Image> findById(String fileName);

	List<Image> findAllByUserId(Long userId);

}
