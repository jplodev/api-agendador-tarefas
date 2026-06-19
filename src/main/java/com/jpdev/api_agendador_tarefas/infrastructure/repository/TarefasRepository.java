package com.jpdev.api_agendador_tarefas.infrastructure.repository;

import com.jpdev.api_agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicio, LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario(String email);
}
