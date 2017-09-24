package com.demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.demo.domainmodel.Link;
import com.demo.persistence.LinkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { 
  "amazon.dynamodb.endpoint=http://localhost:8000/", 
  "amazon.aws.accesskey=test1", 
  "amazon.aws.secretkey=test231" })

public class LinkRepositoryTests {
	
	private DynamoDBMapper dynamoDBMapper;
	 
	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	 
	@Autowired
	LinkRepository repository;
	 
	private static final String SLUG = "QQQ";
	private static final String DESTINATION = "http://cnn.com";
	 
	@Before
	public void setup() throws Exception {
		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
	         
	    CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Link.class);
	    tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
	    try{
	    	amazonDynamoDB.createTable(tableRequest);
	    }
	    catch (Exception ex){}
	 
	    dynamoDBMapper.batchDelete( (List<Link>)repository.findAll());
	}
	 
	@Test
	public void addLinkTest() {
		Link link = new Link(SLUG, DESTINATION);
		repository.save(link);
	 
	    List<Link> result = (List<Link>) repository.findAll();
	         
	    assertTrue("Not empty", result.size() > 0);
	    assertTrue("Contains expected", result.get(0).getDestination().equals(DESTINATION));
	    
	    assertTrue("Repository has one item", repository.count() == 1);
	    assertTrue("Repositoty has my slug", repository.findOne(SLUG).getSlug().equals(SLUG));
	    
	}
}
