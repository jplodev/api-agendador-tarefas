package com.jpdev.api_agendador_tarefas.business.mapper;

import com.jpdev.api_agendador_tarefas.business.dto.TarefasDTO;
import com.jpdev.api_agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefasDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}
