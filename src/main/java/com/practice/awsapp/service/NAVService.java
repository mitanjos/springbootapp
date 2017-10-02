package com.practice.awsapp.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.practice.awsapp.bean.NAVBean;
import com.practice.awsapp.repo.NAVRepo;
import com.practice.awsapp.util.Constants;

@Service
public class NAVService {
	
	private static final Logger logger = LoggerFactory.getLogger(NAVService.class);
	
	@Autowired
	NAVRepo repo;
	
	public List<NAVBean> findAllPrices() {
		List<NAVBean> navList = repo.findAll();
		if(navList!=null && !navList.isEmpty()) {
			for(NAVBean bean:navList) {
				formatForDate(bean);
			}
			return navList;
		}else {
			return null;
		}
	}
	
	public NAVBean findByAMFIId(@PathVariable("amfiId")String amfiId) {
		return repo.findByAmfiId(amfiId);
	}
	
	public List<NAVBean> findByName(@PathVariable("name")String name) {
		List<NAVBean> navList =  repo.findByNameContaining(name);
		if(navList!=null && !navList.isEmpty()) {
			for(NAVBean bean:navList) {
				formatForDate(bean);
			}
		}
		return navList;
	}
	
	public NAVBean createEntry(@RequestBody NAVBean newNav) {
		formatForDate(newNav);
		repo.save(newNav);
		return newNav;
	}
	private void formatForDate(NAVBean bean) {
		if(bean!=null && bean.getDate()==null) {
			try {
				bean.setDate(Constants.DATE_FORMAT.parse(bean.getPriceDate()));
			} catch (ParseException e) {
				logger.error("Can not parse date {}",bean.getPriceDate());
			}
		}
	}
	
}
