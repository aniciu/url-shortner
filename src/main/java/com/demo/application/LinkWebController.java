package com.demo.application;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.LinkService;
import com.demo.domainmodel.Link;

@RestController
public class LinkWebController {

	 @Autowired
	 LinkService linkService;
	 
	 @PostMapping  (value="/v1/link", produces=MediaType.APPLICATION_JSON_VALUE)
	 public Link createLink(HttpServletRequest httpRequest, @RequestBody Link link){
		 
		 //verification 
		 if (link == null || StringUtils.isEmpty(link.getDestination()) ){
			throw new IllegalArgumentException("Must provide destination");
		 }
		 //TODO: validation that it cannot create short cut to a short cut
		 //TODO: check if a link already exists for the desired destination
		 
		 Link returnedLink = linkService.createLink(link);
		 if (returnedLink == null) {
			 throw new IllegalArgumentException("Slug is already take; try another one or let us generate one for you");
		 }
		 return returnedLink;
	 }
	 	 
	 @RequestMapping("/{slug}") 
	 public String redirectShortURL(HttpServletRequest httpRequest, @PathVariable String slug, HttpServletResponse response) throws IOException {

		if (slug == null || StringUtils.isEmpty(slug) ){
			return "Please call POST http://<hostName>/v1/link with new desired destination "
					+ "to get a short http://<hostName>/<slug> url"; 
		}
		
		String destination = linkService.getDestination(slug);
		
		if (!StringUtils.isEmpty(destination)) {
			response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.sendRedirect(destination);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	     
	    return null;
	 }
}
