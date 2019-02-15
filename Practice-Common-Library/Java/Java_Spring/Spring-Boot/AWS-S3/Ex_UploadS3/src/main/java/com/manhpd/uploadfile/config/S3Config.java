/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.uploadfile.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author manh.phanduc
 */
@Configuration
public class S3Config {
    @Value("${jsa.aws.access_key_id}")
    private String awsId;
    
    @Value("${jsa.aws.secret_access_key}")
    private String awsKey;
    
    @Value("${jsa.s3.region}")
    private String region;
    
    @Bean 
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                                                 .withRegion(Regions.fromName(region))
                                                 .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                                                 .build();
        
        return s3Client;
    }
}
