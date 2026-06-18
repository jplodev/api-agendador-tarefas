package com.jpdev.api_agendador_tarefas.business.mapper;

import com.jpdev.api_agendador_tarefas.business.dto.TarefasDTO;
import com.jpdev.api_agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefasDTO(TarefasEntity entity);
}
