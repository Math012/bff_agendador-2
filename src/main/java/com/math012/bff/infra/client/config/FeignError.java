package com.math012.bff.infra.client.config;

import com.math012.bff.infra.execptions.BusinessException;
import com.math012.bff.infra.execptions.ConflictException;
import com.math012.bff.infra.execptions.ResourceNotFoundException;
import com.math012.bff.infra.execptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 409 -> new ConflictException("Erro atributo já existente");
            case 403 -> new ResourceNotFoundException("Erro atributo não encontrado");
            case 401 -> new UnauthorizedException("Erro, usuário não autorizado");
            default -> new BusinessException("Erro de servidor");
        };
    }
}
