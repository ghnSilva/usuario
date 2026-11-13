package com.gustavo.usuario.business;

import com.gustavo.usuario.business.DTO.UsuarioDTO;
import com.gustavo.usuario.business.converter.UsuarioConverter;
import com.gustavo.usuario.infrastructure.entity.Usuario;
import com.gustavo.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
