package com.math012.bff.infra.client;

import com.math012.bff.business.dto.in.TarefaDTORequest;
import com.math012.bff.business.dto.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${email.url}")
public interface EmailClient {


    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOResponse tarefasDTO);

}
