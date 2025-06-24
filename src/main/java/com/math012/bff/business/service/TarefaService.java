package com.math012.bff.business.service;


import com.math012.bff.business.dto.in.TarefaDTORequest;
import com.math012.bff.business.dto.out.TarefaDTOResponse;
import com.math012.bff.infra.client.TarefaClient;
import com.math012.bff.infra.enums.StatusTarefa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TarefaService {


    private final TarefaClient tarefaClient;

    public TarefaDTOResponse gravarTarefa(TarefaDTORequest tarefaDTO, String token){
        return tarefaClient.gravarTarefa(tarefaDTO,token);
    }

    public List<TarefaDTOResponse> buscarTarefaAgendadaPorPeriado(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return tarefaClient.buscarListaTarefaPorPeriado(dataInicial,dataFinal,token);
    }

    public List<TarefaDTOResponse> buscarTarefaPorEmail(String token){
        return tarefaClient.buscarTarefaPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token){
        tarefaClient.deletaTarefaPorId(id,token);

    }

    public TarefaDTOResponse alteraStatusTarefa(StatusTarefa statusTarefa, String idTarefa, String token){
        return tarefaClient.alteraStatusNotificacao(statusTarefa,idTarefa,token);
    }

    public TarefaDTOResponse updateTarefa(TarefaDTORequest tarefaDTO, String idTarefa, String token){
        return tarefaClient.updateDeTarefa(tarefaDTO,idTarefa,token);
    }
}
