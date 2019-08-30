package com.ray.service;

import com.ray.dto.SequenceRange;
import com.ray.mapper.IdGeneratorMapper;
import com.ray.model.IdGenerator;
import org.elasticsearch.common.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存生成器
 *
 * 说明：
 * 1.单机方式
 * 单机下，通过synchronized保证每次只有一个线程进入，通过获取generatorMap在内存中+1
 * 2.集群方式
 * 集群下，每次机器重启后，针对此机器的都更新一个数据段，并保存到generatorMap中，每次获取ID都进行+1操作。
 *
 */
@Service
public class IdGeneratorService {
    @Resource
    private IdGeneratorMapper idGeneratorMapper;
    private final Map<String, SequenceRange> generatorMap = new ConcurrentHashMap<String, SequenceRange>();

    /**
     * 生成ID生成器的主方法
     * @param primaryKey
     * @return
     */
    public synchronized long nextId(String primaryKey) {
        SequenceRange range = generatorMap.get(primaryKey);
        if (range == null || (range.getCursorNo() > range.getEndNo())) {
            System.out.println("从数据库读取");
            if (Strings.isEmpty(primaryKey)) {
                throw new RuntimeException("primaryKey属性不能为空");
            }
            range = this.nextMaxSequenceNo(primaryKey);
            generatorMap.put(primaryKey, range);
        }
        long sequence = range.getCursorNo();
        range.setCursorNo(sequence + 1);
        return sequence;
    }


    // 以下是相关获取ID辅助方法

    /**
     * 调用next方法获取下一步长，
     * 失败调用四次线程休眠500
     * @param primaryKey
     * @return
     */
    private SequenceRange nextMaxSequenceNo(String primaryKey) {
        if (Strings.isEmpty(primaryKey)) {
            throw new RuntimeException("获取业务主键编号的primaryKey属性不能为空");
        }
        SequenceRange range = null;
        int loop = 1;
        while (range == null) {
            try {
                if (loop++ % 4 == 0) {
                    Thread.sleep(500);
                }
                range = next(primaryKey);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }
        return range;
    }

    /**
     *  1.获取步长
     *  2.加步长，更新数据库数据
     * @param primaryKey
     * @return
     */
    private SequenceRange next(String primaryKey) {
        if (Strings.isEmpty(primaryKey)) {
            throw new RuntimeException("没有找到primaryKey=" + primaryKey + "的主键编号生成规则！");
        }
        IdGenerator idGenerator = new IdGenerator();
        idGenerator.setPrimaryKey(primaryKey);
        int row = idGeneratorMapper.updteSequenceNoById(idGenerator);
        if (row > 0) {
            IdGenerator idGeneratorEntity = getIDGenerator(primaryKey);
            if (idGeneratorEntity == null) {
                throw new RuntimeException("没有找到primaryKey=" + primaryKey + "的主键编号生成规则！");
            }
            SequenceRange range = new SequenceRange();
            range.setStartNo(idGeneratorEntity.getSequenceNo() - idGeneratorEntity.getStep() + 1);
            range.setEndNo(idGeneratorEntity.getSequenceNo());
            range.setCursorNo(range.getStartNo());
            return range;
        }
        return null;
    }

    /**
     * 根据主键ID从数据库中获取对应的步长数据
     * @param primaryKey
     * @return
     */
    private IdGenerator getIDGenerator(String primaryKey) {
        IdGenerator query = new IdGenerator();
        query.setPrimaryKey(primaryKey);
        query.setYn(0);
        List<IdGenerator> list = idGeneratorMapper.select(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
