package com.livraria.livraria.component;

import com.livraria.livraria.entity.Item;
import com.livraria.livraria.model.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Component
public class CopyComponent {
	
	public <T> T copyEntityToDto(BaseEntity entity, Class<T> dtoClazz) {
		return Optional.ofNullable(entity).map(e -> {
			try {

				T dto = dtoClazz.getConstructor().newInstance();

				BeanUtils.copyProperties(entity, dto);

				return dto;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
					 InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				return null;
			}
		}).orElse(null);
	}

	public <T> T copyEntityToDto(Item entity, Class<T> dtoClazz) {
		return Optional.ofNullable(entity).map(e -> {
			try {

				T dto = dtoClazz.getConstructor().newInstance();

				BeanUtils.copyProperties(entity, dto);

				return dto;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
					 InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				return null;
			}
		}).orElse(null);
	}
	
	public <T extends BaseEntity> T copyDtoToEntity(Object dto, Class<T> entityClazz) {
		return Optional.ofNullable(dto).map(e -> {
			try {
				
				T entity = entityClazz.getConstructor().newInstance();
				
				BeanUtils.copyProperties(dto, entity);
				
				return entity;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				return null;
			}
		}).orElse(null);
	}

	public <T extends Item> T copyDtoItensToEntity(Object dto, Class<T> entityClazz) {
		return Optional.ofNullable(dto).map(e -> {
			try {

				T entity = entityClazz.getConstructor().newInstance();

				BeanUtils.copyProperties(dto, entity);

				return entity;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				return null;
			}
		}).orElse(null);
	}

	public <T> T copyDtoAuxToEntity(Object dto, Class<T> entityClazz) {
		return Optional.ofNullable(dto).map(e -> {
			try {

				T entity = entityClazz.getConstructor().newInstance();

				BeanUtils.copyProperties(dto, entity);

				return entity;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				return null;
			}
		}).orElse(null);
	}

}
