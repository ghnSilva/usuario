package com.gustavo.usuario.business.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private String rua;
    private Long numero;
    private String cep;
    private String comlemeto;
    private String cidade;
    private String estado;

}
