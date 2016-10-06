package com.sj.repository;

import java.io.Serializable;

import com.sj.model.AbstractModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface GenericElasticSearchRepository<T extends AbstractModel, ID extends Serializable>
		extends ElasticsearchRepository<T, ID> {

	
}
