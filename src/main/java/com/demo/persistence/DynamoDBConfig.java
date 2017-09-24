package com.demo.persistence;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableDynamoDBRepositories
  (basePackages = "com.demo.persistence")

public class DynamoDBConfig {
 
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
 
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
 
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;
 
    @SuppressWarnings("deprecation")
	@Bean
    public AmazonDynamoDB amazonDynamoDB() {
    	
    	AWSCredentials awsCredentials = amazonAWSCredentials();

		AmazonDynamoDB amazonDynamoDB = awsCredentials == null ? 
    			new AmazonDynamoDBClient() : new AmazonDynamoDBClient(awsCredentials);
    			
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
         
        return amazonDynamoDB;
    }
 
    @Bean
    public AWSCredentials amazonAWSCredentials() {
    	
    	AWSCredentials awsCredentials = null;
    	if (!StringUtils.isEmpty(amazonAWSAccessKey)){
    		awsCredentials = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    	}
        return awsCredentials;
    }
}