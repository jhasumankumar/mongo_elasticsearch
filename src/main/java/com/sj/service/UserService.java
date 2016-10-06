package com.sj.service;


import com.sj.model.Content;
import com.sj.model.UserModel;
import org.bson.types.ObjectId;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public interface UserService {

    boolean saveUser(UserModel userModel);
    UserModel getUser(Long id);
    Content saveContent(byte[] fileByte,String fileName,String contentType);
    Content getContentFromMongo(ObjectId id);
    List<Content> getContentFromElasticSearch(ObjectId id, String searchString);
}
