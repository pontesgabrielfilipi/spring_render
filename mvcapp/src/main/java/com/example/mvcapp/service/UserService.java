package com.example.mvcapp.service;

import com.example.mvcapp.model.User;
import com.example.mvcapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // metodos

    // Salvar usuário por id
    public void saveUser(User user){
        userRepository.save(user);
    }

    // Listar todos os usuários
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    // Listar usuário por id
    public User searchUserById (Long id){
        return userRepository.findById(id).orElseThrow (() -> new RuntimeException("Usuário não encontrado"));
//        return userRepository.findById(id).map
    }

    // Atualizar usuário
    public User updateUser(Long id, User updatedUser){
        User user = searchUserById(id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    // Excluir usuário
    public void deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else {
            throw new RuntimeException("Usuário não existente");
        }
    }
}
