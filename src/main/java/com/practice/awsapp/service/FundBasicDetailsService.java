package com.practice.awsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.practice.awsapp.repo.FundBasicDetailsRepo;

@Service
public class FundBasicDetailsService {
	@Autowired
	FundBasicDetailsRepo repo;
	
	@Autowired
	MongoTemplate template;
	
	public List<String> getAllAmfiIdList(){
		return template.getCollection("mfnavlist").distinct("amfiId");
	}
	
}
