package com.math012.bff.controller;


import com.math012.bff.business.dto.in.TarefaDTORequest;
import com.math012.bff.business.dto.out.TarefaDTOResponse;
import com.math012.bff.business.service.TarefaService;
import com.math012.bff.infra.enums.StatusTarefa;
import com.math012.bff.infra.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefa", description = "Cadastro de tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO, @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(tarefaDTO,token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periado", description = "realiza uma busca de tarefas com base em um periado")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefaPorPeriado(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                                               @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(tarefaService.buscarTarefaAgendadaPorPeriado(dataInicial,dataFinal,token));
    }

    @GetMapping
    @Operation(summary = "Busca tarefa por email de usuário", description = "Busca tarefa de usuário por email")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscarTarefaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id", description = "Deleta tarefas com base em seu id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("idTarefa")String id,
                                                  @RequestHeader(name = "Authorization", required = false)String token){
        tarefaService.deletarTarefaPorId(id,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status")
    @Operation(summary = "Altera status da tarefa", description = "Altera o status da tarefa")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusTarefa statusTarefa,
                                                                     @RequestParam("idTarefa")String id,
                                                                     @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(tarefaService.alteraStatusTarefa(statusTarefa,id,token));
    }

    @PutMapping
    @Operation(summary = "Altera os dados da tarefa", description = "Altera os dados da tarefa cadastrada")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaDTOResponse> updateDeTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                            @RequestParam("idTarefa")String id,
                                                            @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(tarefaService.updateTarefa(tarefaDTO,id,token));
    }
}
