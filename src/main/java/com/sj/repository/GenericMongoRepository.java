package com.sj.repository;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.sj.model.AbstractModel;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.mongodb.gridfs.GridFSDBFile;



@NoRepositoryBean
public interface GenericMongoRepository<T extends AbstractModel, ID extends Serializable>
		extends MongoRepository<T, ID> {

	public List<T> find(Query query2, Class<T> class1);
	public String save(InputStream inputStream, String contentType, String filename);
    public GridFSDBFile get (String id);
    public GridFSDBFile getByFilename (String filename);
    public List listFiles();
	
}
