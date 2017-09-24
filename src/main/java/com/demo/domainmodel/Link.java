package com.demo.domainmodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Link")
public class Link {
	private String slug;
	private String destination;
	
	public Link() {
	}
	
	public Link(String slug, String destination) {
		this.slug = slug;
		this.destination = destination;
	}
	    
	@DynamoDBHashKey(attributeName="slug")
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	@DynamoDBAttribute
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
