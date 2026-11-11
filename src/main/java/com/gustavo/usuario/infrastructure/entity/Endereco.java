package com.gustavo.usuario.infrastructure.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rua",length = 100)
    private String rua;
    @Column(name = "numero",length = 100)
    private Long numero;
    @Column(name = "cep", length = 10)
    private String cep;
    @Column(name = "complemeto", length = 10)
    private String comlemeto;
    @Column(name = "cidade", length = 80)
    private String cidade;
    @Column(name = "estado", length = 2)
    private String estado;

}