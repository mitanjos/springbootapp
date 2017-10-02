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
import com.practice.awsapp.service.DataUploadService;
import com.practice.awsapp.service.NAVService;

@RestController
@RequestMapping("/api/nav")
@CrossOrigin
public class NAVController {
	
	private static final Logger logger = LoggerFactory.getLogger(NAVController.class);
	
	
	
	@Autowired
	DataUploadService svc;
	
	@Autowired
	NAVService navSvc;
	
	@RequestMapping(path="/",method=RequestMethod.GET)
	public List<NAVBean> findAllPrices() {
		List<NAVBean> navList = navSvc.findAllPrices();
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
		return navSvc.findByAMFIId(amfiId);
	}
	
	@RequestMapping("/name/{name}")
	public List<NAVBean> findByName(@PathVariable("name")String name) {
		return navSvc.findByName(name);
	}
	
	@RequestMapping("/upload")
	public String dataUpload() {
		svc.loadData();
		return "SUCCESS";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public NAVBean createEntry(@RequestBody NAVBean newNav) {
		navSvc.createEntry(newNav);
		return newNav;
	}
}
