package com.sj.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sj.model.Content;

import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;


@Repository
public class ElasticSearchContentRepositoryImpl extends GenericElasticSearchRepositoryImpl<Content, Serializable> implements
        ElasticSearchContentRepository {

    public ElasticSearchContentRepositoryImpl(ElasticsearchEntityInformation<Content, Serializable> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    @PostConstruct
    public void init(){
        elasticsearchOperations.putMapping(Content.class);
    }

    @Autowired
    public ElasticSearchContentRepositoryImpl(SimpleElasticsearchMappingContext context,
                                              ElasticsearchOperations elasticsearchOperations) {
        this(new MappingElasticsearchEntityInformation(
                context.getPersistentEntity(Content.class)), elasticsearchOperations);

    }

    @Override
    public List<Content> getContent(ObjectId id, String searchString) {

       /* Content content = this.findOne(id);


        GetQuery getQuery = new GetQuery();
        getQuery.setId(id.toString());

        Content queriedObject = elasticsearchOperations.queryForObject(getQuery, Content.class);*/


        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("content")
                .withQuery(QueryBuilders.matchQuery("stringData", searchString)
                        .prefixLength(3))
                .build();
        return elasticsearchOperations.queryForList(searchQuery, Content.class);
    }

}