package com.math012.bff.business.service;


import com.math012.bff.business.dto.in.EnderecoDTORequest;
import com.math012.bff.business.dto.in.LoginDTORequest;
import com.math012.bff.business.dto.in.TelefoneDTORequest;
import com.math012.bff.business.dto.in.UsuarioDTORequest;
import com.math012.bff.business.dto.out.EnderecoDTOResponse;
import com.math012.bff.business.dto.out.TelefoneDTOResponse;
import com.math012.bff.business.dto.out.UsuarioDTOResponse;
import com.math012.bff.business.dto.out.ViaCepDTOResponse;
import com.math012.bff.infra.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public String loginUsuario(LoginDTORequest loginDTORequest){
        return usuarioClient.login(loginDTORequest);
    }

    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(UsuarioDTORequest usuarioDTO, String token){

        return usuarioClient.atualizarDadosUsuario(usuarioDTO,token);
    }

    public EnderecoDTOResponse atualizarEndereco(EnderecoDTORequest enderecoDTO, Long idEndereco, String token){

        return usuarioClient.atualizarEndereco(enderecoDTO,idEndereco,token);
    }


    public TelefoneDTOResponse atualizarTelefone(TelefoneDTORequest telefoneDTO, Long idTelefone, String token){

        return usuarioClient.atualizarTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastrarEndereco(EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.cadastrarEndereco(enderecoDTO,token);
    }


    public TelefoneDTOResponse cadastrarTelefone(TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.cadastrarTelefone(telefoneDTO,token);
    }

    public ViaCepDTOResponse buscarEnderecoPorCep(String cep){
        return usuarioClient.buscarEnderecoViaCep(cep);
    }
}
