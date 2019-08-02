package com.ray.repository;

import com.ray.model.Sku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SkuRepository extends ElasticsearchRepository<Sku,Long> {

    /**
     * 查询价格范围
     * @param startPrice
     * @param endPrice
     * @return
     */
    List<Sku> findByPriceBetween(double startPrice, double endPrice);

}
