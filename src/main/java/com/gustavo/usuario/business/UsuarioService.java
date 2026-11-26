package com.gustavo.usuario.business;

import com.gustavo.usuario.business.DTO.EnderecoDTO;
import com.gustavo.usuario.business.DTO.TelefoneDTO;
import com.gustavo.usuario.business.DTO.UsuarioDTO;
import com.gustavo.usuario.business.converter.UsuarioConverter;
import com.gustavo.usuario.infrastructure.entity.Endereco;
import com.gustavo.usuario.infrastructure.entity.Telefone;
import com.gustavo.usuario.infrastructure.entity.Usuario;
import com.gustavo.usuario.infrastructure.exceptions.ConflictException;
import com.gustavo.usuario.infrastructure.exceptions.ResorceNotFoundException;
import com.gustavo.usuario.infrastructure.repository.EnderecoRepository;
import com.gustavo.usuario.infrastructure.repository.TelefoneRepository;
import com.gustavo.usuario.infrastructure.repository.UsuarioRepository;
import com.gustavo.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioConverter usuarioConverter;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtUtil jwtUtil;
    private  final EnderecoRepository enderecoRepository;
    private  final TelefoneRepository telefoneRepository;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExistente(usuarioDTO.getEmail());

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public void emailExistente (String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }
    }

    public boolean verificaEmailExistente (String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscaUsuarioPorEmail(String email){
      try{
          return usuarioConverter.paraUsuarioDTO(
                  usuarioRepository.findByEmail(email).orElseThrow(()->
                          new ResorceNotFoundException("Email não encontrado" + email)
                    )
          );
      }catch (ResorceNotFoundException e){
          throw new ResorceNotFoundException("Email não encontrado" + email);
      }
    }

    public void deletaUsuarioPorEmail(String email){
          usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token,UsuarioDTO usuarioDTO ){
        String email = jwtUtil.extractUsername(token.substring(7));

        usuarioDTO.setSenha(usuarioDTO.getSenha()!= null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResorceNotFoundException("Email não encontrado"));
        Usuario usuario = usuarioConverter.updateUsuario(usuarioDTO, usuarioEntity);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }

    public EnderecoDTO atualizaDadosEndereco(Long idEndereco, EnderecoDTO enderecoDTO ){
        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResorceNotFoundException("ID do endereço não encontrado" + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO,entity);

        return  usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));

    }
    public TelefoneDTO atualizaDadosTelefone(Long idTelefone, TelefoneDTO telefoneDTO ){
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(() ->
                new ResorceNotFoundException("ID do Telefone não encontrado" + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO,entity);

        return  usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));

    }

}
