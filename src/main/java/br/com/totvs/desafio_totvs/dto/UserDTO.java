package br.com.totvs.desafio_totvs.dto;

import br.com.totvs.desafio_totvs.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
    private String email;
    private String senha;
    private LocalDateTime dataCadastro;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setSenha(user.getSenha());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
}
