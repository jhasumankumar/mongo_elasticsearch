package com.sj.repository;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.sj.model.Content;

@NoRepositoryBean
public interface ElasticSearchContentRepository extends GenericElasticSearchRepository<Content, Serializable> {

    List<Content> getContent(ObjectId id,String searchString);

}
