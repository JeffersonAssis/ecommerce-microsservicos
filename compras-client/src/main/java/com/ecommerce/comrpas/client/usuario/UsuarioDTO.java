package com.ecommerce.comrpas.client.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private ClienteDTO clienteDTO;
    private String Token;
}
