package com.demo.persistence;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.demo.domainmodel.Link;

@EnableScan
public interface LinkRepository extends CrudRepository<Link, String> {
     
    //List<Link> findBySlug(String slug);
}