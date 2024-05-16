package com.example.expericeIA.service;

import com.example.expericeIA.entity.Avaliacao;
import com.example.expericeIA.entity.Login;
import com.example.expericeIA.repository.AvaliacaoRepository;
import com.example.expericeIA.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public List<Login> listarTodos() {
        return loginRepository.findAll();
    }

    public Login salvar(Login login) {
        return loginRepository.save(login);
    }

    public void excluir(Long id) {
        loginRepository.deleteById(id);
    }

    public Optional<Login> buscarPorId(Long id) {
        return loginRepository.findById(id);
    }

    public Login atualizar(Login login, Long id) {
        return loginRepository.findById(id)
                .map(loginExistente -> {
                    loginExistente.setEmailLogin(login.getEmailLogin());
                    loginExistente.setSenhaLogin(login.getSenhaLogin());

                    return loginRepository.save(loginExistente);
                }).orElseThrow(() -> new IllegalStateException("Login com ID " + id + " não existe."));
    }

    public Login atualizarParcialmente(Long id, Map<String, Object> updates) {
        return loginRepository.findById(id)
                .map(loginExistente -> {
                    updates.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Login.class, key);
                        if (field != null) {
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, loginExistente, value);
                        }
                    });
                    return loginRepository.save(loginExistente);
                }).orElseThrow(() -> new IllegalStateException("Login com ID " + id + " não existe."));
    }

}
