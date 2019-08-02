package com.ray.test.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.ray.model.Sku;
import com.ray.repository.SkuRepository;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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

    @Test
    public void findByPriceBetween(){
        List<Sku> list = skuRepository.findByPriceBetween(0,1000);
        System.out.printf(JSON.toJSONString(list));
    }

    /**
     * 自定义查询
     */
    @Test
    public void testMatchQuery(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("tital","小米手机"));
        Page<Sku> items = skuRepository.search(queryBuilder.build());
        System.out.printf(JSON.toJSONString(items));
    }


    @Test
    public void searchByPage(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page,size));

        // 搜索，获取结果
        Page<Sku> items = this.skuRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + items.getTotalPages());
        // 当前页
        System.out.println("当前页：" + items.getNumber());
        // 每页大小
        System.out.println("每页大小：" + items.getSize());

        for (Sku item : items) {
            System.out.println(item);
        }
    }

}
