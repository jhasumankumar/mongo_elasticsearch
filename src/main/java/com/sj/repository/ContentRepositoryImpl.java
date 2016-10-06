package com.sj.repository;

import java.io.Serializable;

import com.sj.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.stereotype.Repository;


@Repository
public class ContentRepositoryImpl extends GenericMongoRepositoryImpl<Content, Serializable> implements
        ContentRepository {

    public ContentRepositoryImpl(
            MongoEntityInformation<Content, Serializable> metadata,
            MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }


    @Autowired
    public ContentRepositoryImpl(MongoMappingContext context,
                                 MongoOperations mongoOperations) {
        this(new MappingMongoEntityInformation(
                context.getPersistentEntity(Content.class)), mongoOperations);

    }


}
