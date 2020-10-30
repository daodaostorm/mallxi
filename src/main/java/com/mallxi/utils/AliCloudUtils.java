package com.mallxi.utils;

import java.io.InputStream;

import com.aliyun.oss.OSSClient;

public class AliCloudUtils {
	
	public static String ENDPOINT = "oss-cn-shanghai.aliyuncs.com";
	public static String ACCESSKEYID = "LTAI4FdmogkQ2ZJVRQLxYhNf";
	public static String ACCESSKEYSECRET = "UcNLTdSKldFvMiz91KNutWYNClf1ee";
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
