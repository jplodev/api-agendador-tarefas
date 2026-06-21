package com.jpdev.api_agendador_tarefas.business;

import com.jpdev.api_agendador_tarefas.business.dto.TarefasDTO;
import com.jpdev.api_agendador_tarefas.business.mapper.TarefasConverter;
import com.jpdev.api_agendador_tarefas.business.mapper.TarefasUpdadeConverter;
import com.jpdev.api_agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.jpdev.api_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.jpdev.api_agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.jpdev.api_agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.jpdev.api_agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final TarefasUpdadeConverter tarefasUpdadeConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravaTarefa(String token, TarefasDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PEDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefaAgendadaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefaPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletaTarefasPorId(String id){
        try{
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado" + id, e.getCause());
        }
    }

    public TarefasDTO alteraStuatus(StatusNotificacaoEnum status, String id){
        try{
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Id não encontrado" + id));
            tarefasEntity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterrar status da tarefa", e.getCause());
        }
    }

    public TarefasDTO atualizaTarefas(TarefasDTO dto, String id){
        try{
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Id não encontrado" + id));
            tarefasUpdadeConverter.updateTarefas(dto, entity);
            dto.setDataAlteracao(LocalDateTime.now());
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Erro em atualizar tarefa", e.getCause());
        }


    }
}
