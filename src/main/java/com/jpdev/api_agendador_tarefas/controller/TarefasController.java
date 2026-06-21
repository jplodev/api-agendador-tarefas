package com.jpdev.api_agendador_tarefas.controller;

import com.jpdev.api_agendador_tarefas.business.TarefasService;
import com.jpdev.api_agendador_tarefas.business.dto.TarefasDTO;
import com.jpdev.api_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravaTarefa(@RequestBody TarefasDTO dto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.gravaTarefa(token, dto));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.buscaTarefaPorEmail(token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefasService.buscaTarefaAgendadaPorPeriodo(dataInicial, dataFinal));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id") String id){
        tarefasService.deletaTarefasPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatus(@RequestParam("status")StatusNotificacaoEnum status,
                                                   @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStuatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> atualizaTarefa(@RequestBody TarefasDTO dto,
                                                     @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.atualizaTarefas(dto, id));
    }
}
