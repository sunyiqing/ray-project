package com.ray.test.mapper;

import com.ray.service.IdGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IDGeneratorServiceTests {

    @Resource
    IdGeneratorService idGeneratorService;
    @Test
    public void testUpdteSequenceNoById() {
        for (int i = 0; i < 150; i++) {
            System.out.println(idGeneratorService.nextId("ACTIVITY_CODE"));
        }
    }



}
