package com.example.expericeIA.service;

import com.example.expericeIA.entity.Cliente;
import com.example.expericeIA.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente usuario) {
        return clienteRepository.save(usuario);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(Cliente usuario, Long id) {
        return clienteRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuario.getNome());
                    usuarioExistente.setEmail(usuario.getEmail());
                    usuarioExistente.setTelefone(usuario.getTelefone());
                    usuarioExistente.setCnpj(usuario.getCnpj());
                    usuarioExistente.setTipoEmpresa(usuario.getTipoEmpresa());


                    return clienteRepository.save(usuarioExistente);
                }).orElseThrow(() -> new IllegalStateException("Usuário com ID " + id + " não existe."));
    }

    public Cliente atualizarParcialmente(Long id, Map<String, Object> updates) {
        return clienteRepository.findById(id)
                .map(usuarioExistente -> {
                    updates.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Cliente.class, key);
                        if (field != null) {
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, usuarioExistente, value);
                        }
                    });
                    return clienteRepository.save(usuarioExistente);
                }).orElseThrow(() -> new IllegalStateException("Usuário com ID " + id + " não existe."));
    }


}