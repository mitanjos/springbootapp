package com.practice.awsapp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.practice.awsapp.bean.NAVBean;
import com.practice.awsapp.repo.NAVRepo;

@Component
public class DataUploadService {

	@Autowired
	NAVRepo repo;

	@Value("${navdata.file.location}")
	private String datafilelocation;

	@Scheduled(cron = "0 0 10 * * ?")
	public void loadData() {
		System.out.println("Loading data @ " + new Date());
		if(datafilelocation==null || "".equals(datafilelocation.trim())) {
			datafilelocation = "";
		}
		File dataDir = new File(datafilelocation);
		if (dataDir.isDirectory()) {
			File[] fileList = dataDir.listFiles();
			for (File file : fileList) {
				processFile(file);
			}
		}
	}

	private void processFile(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (i != 0) {
					if (line.lastIndexOf(';') > 0) {
						NAVBean bean = buildRecordFromString(line);
						if (bean != null) {
							repo.save(bean);
						}
					}
				}
				i++;
			}
			file.delete();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static NAVBean buildRecordFromString(String str) {
		String[] values = str.split(";");
		System.out.println(values.length);
		if (values.length == 8) {
			NAVBean bean = new NAVBean();
			bean.setAmfiId(values[0]);
			bean.setPayoutISIN(values[1]);
			bean.setReInvISIN(values[2]);
			bean.setName(values[3]);
			bean.setNavVal(NumberUtils.isNumber(values[4]) ? Double.valueOf(values[4]) : 0d);
			bean.setRepurchasePrice(NumberUtils.isNumber(values[5]) ? Double.valueOf(values[5]) : 0d);
			bean.setSalePrice(NumberUtils.isNumber(values[6]) ? Double.valueOf(values[6]) : 0d);
			bean.setPriceDate(values[7]);
			return bean;
		}
		return null;
	}
}
