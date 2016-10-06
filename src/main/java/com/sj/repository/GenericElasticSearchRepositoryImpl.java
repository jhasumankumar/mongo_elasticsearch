package com.sj.repository;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.sj.model.AbstractModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;

import org.springframework.data.repository.NoRepositoryBean;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;


@NoRepositoryBean
public class GenericElasticSearchRepositoryImpl<T extends AbstractModel, ID extends Serializable>
		extends AbstractElasticsearchRepository<T, Serializable> implements
		GenericElasticSearchRepository<T, Serializable> {

	public GenericElasticSearchRepositoryImpl() {
	}

	public GenericElasticSearchRepositoryImpl(ElasticsearchEntityInformation<T, Serializable> metadata, ElasticsearchOperations elasticsearchOperations) {
		super(metadata, elasticsearchOperations);
	}

	@Override
	public String stringIdRepresentation(Serializable serializable) {

		return serializable.toString();
	}

	public GenericElasticSearchRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
		super(elasticsearchOperations);
	}

}
