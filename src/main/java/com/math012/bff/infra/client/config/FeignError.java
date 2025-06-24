package com.math012.bff.infra.client.config;

import com.math012.bff.infra.execptions.BusinessException;
import com.math012.bff.infra.execptions.ConflictException;
import com.math012.bff.infra.execptions.IllegalArgumentException;
import com.math012.bff.infra.execptions.ResourceNotFoundException;
import com.math012.bff.infra.execptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String msg = msgError(response);

        return switch (response.status()) {
            case 400 -> new IllegalArgumentException("Erro: " + msg);
            case 401 -> new UnauthorizedException("Erro: " + msg);
            case 409 -> new ConflictException("Erro: " + msg);
            case 403 -> new ResourceNotFoundException("Erro: " + msg);
            default -> new BusinessException("Erro: " + msg);
        };
    }

    private String msgError(Response response){
        try {
            if (Objects.isNull(response)){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
