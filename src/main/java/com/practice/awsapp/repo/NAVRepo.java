package com.practice.awsapp.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.awsapp.bean.NAVBean;

public interface NAVRepo extends MongoRepository<NAVBean, String>{
	public NAVBean findByAmfiId(String amfiId);
	public List<NAVBean> findByNameContaining(String name);
}
