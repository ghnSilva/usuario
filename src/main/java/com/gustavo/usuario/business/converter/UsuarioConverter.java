package com.gustavo.usuario.business.converter;


import com.gustavo.usuario.business.DTO.EnderecoDTO;
import com.gustavo.usuario.business.DTO.TelefoneDTO;
import com.gustavo.usuario.business.DTO.UsuarioDTO;
import com.gustavo.usuario.infrastructure.entity.Endereco;
import com.gustavo.usuario.infrastructure.entity.Telefone;
import com.gustavo.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();

    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .comlemeto(enderecoDTO.getComlemeto())
                .estado(enderecoDTO.getEstado())
                .build();
    }
    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();

    }
   public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }
    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> endereco){
        return endereco.stream().map(this::paraEnderecoDTO).toList();

    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .comlemeto(endereco.getComlemeto())
                .estado(endereco.getEstado())
                .build();
    }
    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();

    }
    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuario, Usuario entity){
        return Usuario.builder()
                .nome(usuario.getNome() != null ? usuario.getNome() : entity.getNome())
                .email(usuario.getEmail() != null ? usuario.getEmail() : entity.getEmail())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : entity.getSenha())
                .id(entity.getId())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO endereco, Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .rua(endereco.getRua() != null ? endereco.getRua() : entity.getRua())
                .numero(endereco.getNumero() != null ? endereco.getNumero() : entity.getNumero())
                .estado(endereco.getEstado() != null ? endereco.getEstado() : entity.getEstado())
                .cep(endereco.getCep() != null ? endereco.getCep() : entity.getCep())
                .comlemeto(endereco.getComlemeto() != null ? endereco.getComlemeto() : entity.getComlemeto())
                .cidade(endereco.getCidade() != null ? endereco.getCidade() : entity.getCidade())
                .build();
    }
    public Telefone updateTelefone ( TelefoneDTO telefone, Telefone entity){
        return Telefone.builder()
                .id(entity.getId())
                .ddd(telefone.getDdd() != null ? telefone.getId() : entity.getDdd())
                .numero(telefone.getNumero() != null ? telefone.getNumero() : entity.getNumero())
                .build();
    }

}
