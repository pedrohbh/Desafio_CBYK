package br.com.totvs.desafio_totvs.service;

import br.com.totvs.desafio_totvs.dto.UserDTO;
import br.com.totvs.desafio_totvs.model.User;
import br.com.totvs.desafio_totvs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public List<UserDTO> getAll()
    {
        return userRepository.findAll().stream().map(UserDTO::convert).collect(Collectors.toList());
    }

    public UserDTO save(UserDTO userDTO)
    {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public void delete(long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        userRepository.delete(user);
    }
}
