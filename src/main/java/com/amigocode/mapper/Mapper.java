package com.amigocode.mapper;

import java.util.List;

public interface Mapper<Entity,Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
    List<Entity>toEntityList(List<Dto> dtos);
    List<Dto>toDtoList(List<Entity> entities);
}
