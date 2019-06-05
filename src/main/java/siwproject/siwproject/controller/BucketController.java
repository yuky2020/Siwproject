package siwproject.siwproject.controller;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;

import com.amazonaws.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping
public class BucketController {
	 private AmazonClient amazonClient;

	    @Autowired
	    BucketController(AmazonClient amazonClient) {
	        this.amazonClient = amazonClient;
	    }

	    @PostMapping("/uploadFile")
	    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
	    	String url1 = this.amazonClient.uploadFile(file);
	    	String url = "https://silph.s3.eu-west-3.amazonaws.com"+url1.substring(40);
	    	System.out.println(url);
	    	return url1;
	    }

	    @DeleteMapping("/deleteFile")
	    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
	        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	    }
}

