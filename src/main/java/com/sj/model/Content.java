package com.sj.model;


import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.*;

@org.springframework.data.mongodb.core.mapping.Document(collection = "resume")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "content", type = "test", shards = 1, replicas = 0, refreshInterval = "-1")
public class Content extends AbstractModel {


    public Content(){

    }

    public Content(byte[] data,String fileName,String contentType,String userName){
        this.data = data;
        this.fileName = fileName;
        this.contentType = contentType;
        this.userName = userName;
        this.data = Base64.encodeBase64(data);
        stringData = new String(data);
    }

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @org.springframework.data.mongodb.core.mapping.Field(value = "userName")
    @Field(type= FieldType.String, index= FieldIndex.analyzed, analyzer="english", searchAnalyzer="english")
    private String userName;

    @org.springframework.data.mongodb.core.mapping.Field(value = "contentType")
    @Field(type= FieldType.String, index= FieldIndex.analyzed, analyzer="english", searchAnalyzer="english")
    private String contentType;

    private Date createTime;
    private Date updateTime;

    @org.springframework.data.mongodb.core.mapping.Field(value = "fName")
    @Field(type= FieldType.String, index= FieldIndex.analyzed, analyzer="english", searchAnalyzer="english")
    private String fileName;


    @Mapping(mappingPath = "/mappings/field-mappings.json")
    @Field(type= FieldType.Attachment, index= FieldIndex.analyzed, analyzer="english", searchAnalyzer="english")
    private String stringData;

    private byte[] data;

    public ObjectId getId() {
        return id;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }



    public String getUserName() {
        return userName;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public String getFileName() {
        return fileName;
    }


    public byte[] getData() {
        return data;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setData(byte[] data) {
        //	this.imgBase64 = Base64.encode(imageData);
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}