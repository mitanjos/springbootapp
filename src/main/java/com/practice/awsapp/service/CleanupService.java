package com.practice.awsapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.practice.awsapp.bean.NAVBean;
import com.practice.awsapp.repo.NAVRepo;

@Service
public class CleanupService {
	
	private static final Logger logger = LoggerFactory.getLogger(CleanupService.class);
	
	@Autowired
	MongoTemplate template;
	
	@Autowired
	NAVRepo repo;
	
	public void cleanup() {
		logger.info("Starting DB cleanup");
		List<String> dateList = template.getCollection("mfnavlist").distinct("priceDate");
		logger.info("For distinct date we have {} records",dateList.size());
		for(String date:dateList) {
			cleanForDate(date);
		}
	}
	
	public void cleanForDate(String date) {
		logger.info("Cleaning up for {}",date);
		List<String> amfiIdList = new ArrayList<>();
		List<NAVBean> navList = repo.findByPriceDate(date);
		for(NAVBean bean:navList) {
			if(amfiIdList.contains(bean.getAmfiId())) {
				logger.info("Deleting duplicate for {} on {}",bean.getAmfiId(),date);
				repo.delete(bean);
			}
			amfiIdList.add(bean.getAmfiId());
		}
	}
}
