package com.math012.bff.infra.client;


import com.math012.bff.business.dto.in.EnderecoDTORequest;
import com.math012.bff.business.dto.in.LoginDTORequest;
import com.math012.bff.business.dto.in.TelefoneDTORequest;
import com.math012.bff.business.dto.in.UsuarioDTORequest;
import com.math012.bff.business.dto.out.EnderecoDTOResponse;
import com.math012.bff.business.dto.out.TelefoneDTOResponse;
import com.math012.bff.business.dto.out.UsuarioDTOResponse;
import com.math012.bff.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTORequest);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestParam("id")Long id,
                                          @RequestHeader("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestParam("id")Long id,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestHeader("Authorization") String token);



    @PostMapping("/telefone")
    TelefoneDTOResponse cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarEnderecoViaCep(@PathVariable String cep);

}
