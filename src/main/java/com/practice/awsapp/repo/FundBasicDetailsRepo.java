package com.practice.awsapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.awsapp.bean.FundBasicDetails;

public interface FundBasicDetailsRepo extends MongoRepository<FundBasicDetails, String>{
	
}
