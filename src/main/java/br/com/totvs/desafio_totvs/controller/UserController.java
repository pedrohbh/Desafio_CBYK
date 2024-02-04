package br.com.totvs.desafio_totvs.controller;

import br.com.totvs.desafio_totvs.dto.UserDTO;
import br.com.totvs.desafio_totvs.model.User;
import br.com.totvs.desafio_totvs.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;
    public static List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initiateList()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("edurado@email.com");
        userDTO.setSenha("123");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setSenha("456");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO3 = new UserDTO();
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
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO)
    {
        return userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        userService.delete(id);
    }

    @GetMapping("/antigo")
    public List<UserDTO> getUsersAntigo()
    {
        return usuarios;
    }
}
