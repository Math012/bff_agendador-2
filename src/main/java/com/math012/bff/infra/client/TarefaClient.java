package com.math012.bff.infra.client;

import com.math012.bff.business.dto.in.TarefaDTORequest;
import com.math012.bff.business.dto.out.TarefaDTOResponse;
import com.math012.bff.infra.enums.StatusTarefa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDTOResponse gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO, @RequestHeader("Authorization")String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscarListaTarefaPorPeriado(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                        @RequestHeader("Authorization")String token);

    @GetMapping
    List<TarefaDTOResponse> buscarTarefaPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("idTarefa")String id,
                           @RequestHeader("Authorization")String token);

    @PutMapping("/status")
    TarefaDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusTarefa statusTarefa,
                                              @RequestParam("idTarefa")String id,
                                              @RequestHeader("Authorization")String token);

    @PutMapping
    TarefaDTOResponse updateDeTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                     @RequestParam("idTarefa")String id,
                                     @RequestHeader("Authorization")String token);

}
