package com.jpdev.api_agendador_tarefas.business.mapper;

import com.jpdev.api_agendador_tarefas.business.dto.TarefasDTO;
import com.jpdev.api_agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdadeConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
