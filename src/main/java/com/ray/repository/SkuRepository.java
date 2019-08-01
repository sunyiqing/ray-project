package com.ray.repository;

import com.ray.model.Sku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuRepository extends ElasticsearchRepository<Sku,Long> {

}
