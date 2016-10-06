package com.sj.service;

import com.sj.model.Content;
import com.sj.model.UserModel;
import com.sj.repository.ContentRepository;
import com.sj.repository.ElasticSearchContentRepository;
import com.sj.repository.UserAddressDao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAddressDao userAddressDao;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ElasticSearchContentRepository elasticSearchContentRepository;
    @Autowired
    protected ElasticsearchOperations elasticsearchOperations;

    @Override
    @Transactional
    public boolean saveUser(UserModel userModel) {
        userAddressDao.save(userModel);
        return true;
    }

    @Override
    public UserModel getUser(Long id) {
        return userAddressDao.findOne(id);
    }

    @Override
    public Content saveContent(byte[] fileByte,String fileName,String contentType) {
        Content content = new Content(fileByte,fileName,contentType,"Suman");
        content = contentRepository.save(content);


        //elasticSearchContentRepository.save(content);


        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(content.getId().toString());
        indexQuery.setObject(content);

        //creating mapping
        elasticsearchOperations.putMapping(Content.class);
        //indexing document
        elasticsearchOperations.index(indexQuery);
        //refresh
        elasticsearchOperations.refresh(Content.class);
        return content;
    }

    @Override
    public Content getContentFromMongo(ObjectId id){

        return contentRepository.findOne(id);
    }

    @Override
    public List<Content> getContentFromElasticSearch(ObjectId id, String searchString){
        return elasticSearchContentRepository.getContent(id,searchString);
    }
}
