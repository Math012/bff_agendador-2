package com.math012.bff.business.service;

import com.math012.bff.business.dto.in.LoginDTORequest;
import com.math012.bff.business.dto.in.TarefaDTORequest;
import com.math012.bff.business.dto.out.TarefaDTOResponse;
import com.math012.bff.infra.enums.StatusTarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {


    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.cron}")
    private String emailCron;
    @Value("${senha.cron}")
    private String senhaCron;


    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefaDaProximaHora(){
        String token = login(converterLogin());
        LocalDateTime horaFutura = LocalDateTime.now();
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(8).plusMinutes(5);
        List<TarefaDTOResponse> listaTarefas = tarefaService.buscarTarefaAgendadaPorPeriado(horaFutura,horaFuturaMaisCinco,token);
        listaTarefas.forEach(tarefa ->{
            emailService.enviaEmail(tarefa);
            System.out.println("Tarefa enviada");
            tarefaService.alteraStatusTarefa(StatusTarefa.NOTIFICADO,tarefa.getId(),token);
            System.out.println("Status settado para notificado");
            tarefa.setDataAlteracao(LocalDateTime.now());
            tarefaService.updateTarefa(converterTarefa(tarefa), tarefa.getId(), token);
            System.out.println("Adicionando a data da alteração");
        });
    }

    public String login(LoginDTORequest loginDTORequest){
        return usuarioService.loginUsuario(loginDTORequest);
    }

    public LoginDTORequest converterLogin(){
        return LoginDTORequest.builder()
                .email(emailCron)
                .senha(senhaCron)
                .build();
    }

    public TarefaDTORequest converterTarefa(TarefaDTOResponse tarefaDTOResponse){
        return TarefaDTORequest.builder()
                .dataAlteracao(tarefaDTOResponse.getDataAlteracao())
                .build();
    }
}
