package com.jpdev.api_agendador_tarefas.controller;

import com.jpdev.api_agendador_tarefas.business.TarefasService;
import com.jpdev.api_agendador_tarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
