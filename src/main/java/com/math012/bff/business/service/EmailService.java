package com.math012.bff.business.service;


import com.math012.bff.business.dto.out.TarefaDTOResponse;
import com.math012.bff.infra.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefaDTOResponse tarefaDTO){
        emailClient.enviarEmail(tarefaDTO);
    }

}