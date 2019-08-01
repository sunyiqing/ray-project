package com.ray.test;

import com.ray.model.Sku;
import com.ray.repository.SkuRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private SkuRepository skuRepository;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Sku.class);
//        elasticsearchTemplate.deleteIndex(Sku.class);
    }


    @Test
    public void insert() {
        Sku sku = new Sku(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        skuRepository.save(sku);
    }


    @Test
    public void insertList() {
        List<Sku> list = Lists.newArrayList();
        list.add(new Sku(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Sku(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        skuRepository.saveAll(list);
    }



    @Test
    public void update(){
        Sku item = new Sku(1L, "苹果XSMax", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        skuRepository.save(item);
    }

    @Test
    public void testQueryAll(){
        Iterable<Sku> list = this.skuRepository.findAll(Sort.by("id").ascending());
    }


}
