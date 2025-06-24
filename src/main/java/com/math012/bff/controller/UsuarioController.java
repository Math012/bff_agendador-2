package com.math012.bff.controller;


import com.math012.bff.business.dto.in.EnderecoDTORequest;
import com.math012.bff.business.dto.in.LoginDTORequest;
import com.math012.bff.business.dto.in.TelefoneDTORequest;
import com.math012.bff.business.dto.in.UsuarioDTORequest;
import com.math012.bff.business.dto.out.EnderecoDTOResponse;
import com.math012.bff.business.dto.out.TelefoneDTOResponse;
import com.math012.bff.business.dto.out.UsuarioDTOResponse;
import com.math012.bff.business.service.UsuarioService;
import com.math012.bff.infra.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Realiza o login de usuário")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody LoginDTORequest usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Busca dados de usuário por email", description = "Busca dados de usuário através do email do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuário por id", description = "Realiza a deleção de usuários através do email cadastrado")
    @ApiResponse(responseCode = "200", description = "Usuario deletado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token){
        usuarioService.deletarUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuário", description = "Atualiza dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(usuarioDTO,token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço de usuário", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestParam("id")Long id,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(enderecoDTO, id, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone de usuário", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestParam("id")Long id,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(telefoneDTO, id, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de usuário", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(enderecoDTO, token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuário", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastrarEndereco(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(telefoneDTO, token));
    }

}
