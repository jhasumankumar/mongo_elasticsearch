package com.sj.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

import com.sj.model.Content;

@NoRepositoryBean
public interface ContentRepository extends GenericMongoRepository<Content, Serializable> {
}
