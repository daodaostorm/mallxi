package com.mallxi.utils;

import java.io.InputStream;

import com.aliyun.oss.OSSClient;

public class AliCloudUtils {
	
	public static String ENDPOINT = "";
	public static String ACCESSKEYID = "";
	public static String ACCESSKEYSECRET = "";
	public static String BUCKETNAME = "mallsvr";
	public static String KEY = "master/images/";

	
	public static String uploadStreamToCloud(InputStream input, String fileName) {

		String dateStr = Long.toString(System.currentTimeMillis() / 1000L) + "-" + fileName;
		String retrunPicUrl = "";
		//InputStream input = file.getInputStream();
		 // 创建OSSClient实例
		
		OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
		// 上传文件流
		ossClient.putObject(BUCKETNAME, KEY + dateStr, input);
		ossClient.shutdown();
		retrunPicUrl = "https://" +  BUCKETNAME + "." + ENDPOINT + "/" +KEY + dateStr;
		return retrunPicUrl;
		
	}

}
