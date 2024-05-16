package com.example.expericeIA.controller;

import com.example.expericeIA.entity.Login;
import com.example.expericeIA.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //GET
    @GetMapping
    public List<Login> listarUsuarios() {
        return loginService.listarTodos();
    }

    //POST
    @PostMapping
    public ResponseEntity<Login> criarUsuario(@RequestBody Login login) {
        Login novoUsuario = loginService.salvar(login);
        return ResponseEntity.ok(novoUsuario);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Login> atualizarLogin(@PathVariable Long id, @RequestBody Login login) {
        Login loginAtualizado = loginService.atualizar(login, id);
        return ResponseEntity.ok(loginAtualizado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        loginService.excluir(id);
        return ResponseEntity.ok().build();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Login> consultarUsuarioPorId(@PathVariable Long id) {
        Login login = loginService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return ResponseEntity.ok(login);
    }

    //OPTIONS
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(@PathVariable Long id) {
        boolean loginExiste = loginService.buscarPorId(id).isPresent();
        if (!loginExiste) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Login> atualizarParcialmenteUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Login loginAtualizado = loginService.atualizarParcialmente(id, updates);
        return ResponseEntity.ok(loginAtualizado);
    }

    //HEAD
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> consultarLoginHead(@PathVariable Long id) {
        Optional<Login> login = loginService.buscarPorId(id);
        if (!login.isPresent()) {
            return ResponseEntity.notFound().build();  // Retorna 404 Not Found
        } else {
            return ResponseEntity.ok().build();  // Retorna 200 OK se encontrado
        }
    }

}
