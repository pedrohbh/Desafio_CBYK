package br.com.totvs.desafio_totvs.controller;

import br.com.totvs.desafio_totvs.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    public static List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initiateList()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setEmail("edurado@email.com");
        userDTO.setSenha("123");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setSenha("456");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setSenha("789");
        userDTO3.setDataCadastro(LocalDateTime.now());

        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }

    @GetMapping
    public List<UserDTO> getUsers()
    {
        return usuarios;
    }
}
