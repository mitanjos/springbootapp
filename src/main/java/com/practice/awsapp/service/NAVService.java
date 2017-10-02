package com.practice.awsapp.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		return processResult(navList);
	}
	
	public List<NAVBean> findByAMFIId(@PathVariable("amfiId")String amfiId) {
		List<NAVBean> navList =   repo.findByAmfiId(amfiId);
		return processResult(navList);
	}
	
	public List<NAVBean> findByName(@PathVariable("name")String name) {
		List<NAVBean> navList =  repo.findByNameContaining(name);
		return processResult(navList);
	}
	
	public NAVBean createEntry(@RequestBody NAVBean newNav) {
		formatForDate(newNav);
		repo.save(newNav);
		return newNav;
	}
	private List<NAVBean> processResult(List<NAVBean> navList){
		List<NAVBean> navRetList = new ArrayList<>();
		Map<String, List<NAVBean>> navListByAmfi = new TreeMap<>();
		if(navList==null || navList.isEmpty())
			return navList;
		for(NAVBean bean:navList) {
			String keyStr = bean.getAmfiId()+":"+bean.getPriceDate();
			if(navListByAmfi.containsKey(keyStr)) {
				logger.info("Will delete: {}",bean);
			}else {
				List<NAVBean> navBeanList = new ArrayList<>();
				navBeanList.add(bean);
				navListByAmfi.put(keyStr, navBeanList);
				formatForDate(bean);
				navRetList.add(bean);
			}
		}
		return navRetList;
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
