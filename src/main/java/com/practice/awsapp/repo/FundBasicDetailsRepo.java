package com.practice.awsapp.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.practice.awsapp.bean.FundBasicDetails;

public interface FundBasicDetailsRepo extends MongoRepository<FundBasicDetails, String>{
	
}
