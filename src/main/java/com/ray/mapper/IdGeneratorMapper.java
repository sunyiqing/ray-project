package com.ray.mapper;

import com.ray.model.IdGenerator;
import com.ray.util.MyMapper;

public interface IdGeneratorMapper extends MyMapper<IdGenerator> {
    int updteSequenceNoById(IdGenerator idGenerator);
}