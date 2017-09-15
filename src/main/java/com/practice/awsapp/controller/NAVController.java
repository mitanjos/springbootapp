package com.practice.awsapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.awsapp.bean.NAVBean;
import com.practice.awsapp.repo.NAVRepo;
import com.practice.awsapp.service.DataUploadService;

@RestController
@RequestMapping("/api/nav")
@CrossOrigin
public class NAVController {
	
	private static final Logger logger = LoggerFactory.getLogger(NAVController.class);
	
	@Autowired
	NAVRepo repo;
	
	@Autowired
	DataUploadService svc;
	
	@RequestMapping(path="/",method=RequestMethod.GET)
	public List<NAVBean> findAllPrices() {
		List<NAVBean> navList = repo.findAll();
		if(navList!=null && !navList.isEmpty()) {
			return navList;
		}else {
			logger.info("Data not available so loading new data");
			svc.loadData();
			return null;
		}
	}
	
	@RequestMapping(path="/{amfiId}",method=RequestMethod.GET)
	public NAVBean findByAMFIId(@PathVariable("amfiId")String amfiId) {
		return repo.findByAmfiId(amfiId);
	}
	
	@RequestMapping("/name/{name}")
	public List<NAVBean> findByName(@PathVariable("name")String name) {
		return repo.findByNameContaining(name);
	}
	
	@RequestMapping("/upload")
	public String dataUpload() {
		svc.loadData();
		return "SUCCESS";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public NAVBean createEntry(@RequestBody NAVBean newNav) {
		repo.save(newNav);
		return newNav;
	}
}
