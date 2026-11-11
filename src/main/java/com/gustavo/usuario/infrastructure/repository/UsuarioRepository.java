package com.gustavo.usuario.infrastructure.repository;


import com.gustavo.usuario.infrastructure.entity.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    boolean existsByEmail(String email);

    Optional<Usuarios> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);


}
