package com.practice.awsapp.repo.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.practice.awsapp.repo.NAVRepo;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RepoTester {
	
	private static final String amfiId = "119585";
	@Autowired
	NAVRepo repo;
	
	Logger logger = LoggerFactory.getLogger(RepoTester.class);
	
	@Test
	public void testIfEntityExistsByName() {
		Assert.assertNotNull(repo.findByNameContaining("SBI"));
	}
	
	@Test
	public void testIfEntityExistsByAmfiId() {
		Assert.assertNotNull(repo.findByAmfiId(amfiId));
	}
}
