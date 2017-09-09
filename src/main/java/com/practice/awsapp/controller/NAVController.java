package com.practice.awsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.awsapp.bean.NAVBean;
import com.practice.awsapp.repo.NAVRepo;

@RestController
@RequestMapping("/api/nav")
public class NAVController {
	
	@Autowired
	NAVRepo repo;
	
	@RequestMapping(path="/",method=RequestMethod.GET)
	public List<NAVBean> findAllPrices() {
		return repo.findAll();
	}
	
	@RequestMapping(path="/{amfiId}",method=RequestMethod.GET)
	public NAVBean findByAMFIId(@PathVariable("amfiId")String amfiId) {
		return repo.findByAmfiId(amfiId);
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public NAVBean createEntry(@RequestBody NAVBean newNav) {
		repo.save(newNav);
		return newNav;
	}
}
