package com.math012.bff.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.math012.bff.infra.enums.StatusTarefa;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTORequest {
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
}
