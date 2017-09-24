package com.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.demo.domainmodel.Link;

import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@WebMvcTest(value= LinkWebControllerTests.class)
public class LinkWebControllerTests {
	//private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	//		MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired	
	private MockMvc mockMvc;

	//@Autowired
	//private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
	//	this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addNewLink() throws Exception {
		Link link = new Link();
		link.setDestination("destination");
		
		//mockMvc.perform(post("/v1/link", link))
	    //            .andExpect(status().is3xxRedirection());            
	}
}