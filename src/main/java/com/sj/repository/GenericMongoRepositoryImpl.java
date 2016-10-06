package com.sj.repository;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.sj.model.AbstractModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;


@NoRepositoryBean
public class GenericMongoRepositoryImpl<T extends AbstractModel, ID extends Serializable>
		extends SimpleMongoRepository<T, Serializable> implements
		GenericMongoRepository<T, Serializable> {

	@Autowired
	protected GridFsOperations gridOperation;



	public GenericMongoRepositoryImpl(
			MongoEntityInformation<T, Serializable> metadata,
			MongoOperations mongoOperations) {
		super(metadata, mongoOperations);

	}

	@Override
	public List<T> find(Query query2, Class<T> class1) {
		return find(query2, class1);
		
	}

	public String save(InputStream inputStream, String contentType,
			String filename) {

		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", filename);
		metaData.put("meta2", contentType);

		GridFSFile file = gridOperation.store(inputStream, filename, metaData);

		return file.getId().toString();
	}

	@Override
	public GridFSDBFile get(String id) {

		System.out.println("Finding by ID: " + id);
		return gridOperation.findOne(new Query(Criteria.where("_id").is(
				new ObjectId(id))));
	}

	@Override
	public List listFiles() {

		return gridOperation.find(null);
	}

	@Override
	public GridFSDBFile getByFilename(String filename) {
		return gridOperation.findOne(new Query(Criteria.where("fileName").is(
				filename)));
	}
}
