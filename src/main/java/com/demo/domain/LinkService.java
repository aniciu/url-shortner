package com.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domainmodel.Link;
import com.demo.persistence.LinkRepository;
import com.demo.utilities.Base62;

@Service
public class LinkService {
	 
	@Autowired
	LinkRepository linkRepo;
	
	@Autowired
	SequenceGeneratorService idIncrementService;

	final int RETRY_UNIQUE_SLUG = 3; 
	
	public Link createLink(Link link) {
	    String slug = link.getSlug();

	    // if the request did not include a slug, make a new one
	    if (slug == null) {
	    	slug = generateSlug();
	    }

	    //make sure that new slug is unique, and its slot was not occupied previously by a user suggested slug
	    for (int i = 0; !isUnique (slug) && i< RETRY_UNIQUE_SLUG; ++i){
	    	slug = generateSlug();
    	}
    		
		if (slug == null) {
			//log error
			return null;
		}
			
	    link.setSlug(slug);
	    linkRepo.save(link);
	    
	    return link;
	}

	private boolean isUnique(String slug) {
		Link found = linkRepo.findOne(slug) ;
	    return (found == null);
	}

	public String getDestination(String slug) {
		Link link = linkRepo.findOne(slug);
		if (link != null) {
			return link.getDestination();
		}
		return null;
	}

	public String generateSlug() {
		long newId = idIncrementService.getNextId();
	    return Base62.fromLong(newId);
	}
	
}
