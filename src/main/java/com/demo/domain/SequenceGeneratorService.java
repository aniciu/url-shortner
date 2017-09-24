package com.demo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.demo.domain.exception.ApplicationException;

@Service
public class SequenceGeneratorService {
	
	//primary sequence generator 
	@Value("${sequence-gen-service1_host}")
	private String SequenceGeneratorHost1;
	@Value("${sequence-gen-service1_start}")
	private long SequenceGeneratorMin1;

	//fallback sequence generator 
	@Value("${sequence-gen-service2_host}")
	private String SequenceGeneratorHost2;
	@Value("${sequence-gen-service1_start}")
	private long SequenceGeneratorMin2;
	
	@Value("${sequence-gen-range}")
	private long SequenceGeneratorRange;
	 
	public long getNextId() {
	    
		long nextId = -1;
		
		try {
			nextId = getNextId(SequenceGeneratorHost1 + "v1/nextid/");
			if (inRange(nextId, 1)) {
				return nextId;
			}
			else {
				//log error
			}
		}
		catch (Exception e) {
			//log error
		}
		
		//use fallback generator
		try {
			nextId = getNextId(SequenceGeneratorHost2 + "v1/nextid/");
			if (inRange(nextId, 1)) {
				return nextId;
			}
			else {
				//log error
			}
		}
		catch (Exception e) {
			//log error
			throw new ApplicationException ("0", "We have trouble generating a shortName, please suggest one yourself or retry in few minutes", "");
		}
		
		return nextId;
	}
	
	private long getNextId(String hostName) {
		
		long nextId = -1;
	
		nextId = new RestTemplate().getForEntity(hostName, Long.class).getBody();
		if (!inRange(nextId, 1)) {
			//log error; this number overlaps with the next generator 
			nextId = -1;
		}
	    return nextId;
	}

	private boolean inRange(long nextId, int poolNumber) {
		
		if (poolNumber < 1 && poolNumber >2){
			//log error
			return false;
		}
		long min = (poolNumber == 1) ? SequenceGeneratorMin1 : SequenceGeneratorMin2;
		long max = min + SequenceGeneratorRange;
		return (nextId >= min && nextId < max);
	}
}
