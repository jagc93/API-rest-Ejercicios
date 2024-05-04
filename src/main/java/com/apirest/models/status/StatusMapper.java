package com.apirest.models.status;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.apirest.config.MyMapperConfig;
import com.apirest.entities.Status;

@Mapper(config = MyMapperConfig.class)
public interface StatusMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateFromRequest(StatusRequest request, @MappingTarget Status entity);

	Status toEntity(StatusRequest request);

	StatusDto toDto(Status entity);

	List<StatusDto> toListDto(List<Status> entities);
}
