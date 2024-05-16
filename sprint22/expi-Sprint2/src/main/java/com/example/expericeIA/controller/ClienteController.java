package com.example.expericeIA.controller;

import com.example.expericeIA.service.ClienteService;
import com.example.expericeIA.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //GET
    @GetMapping
    public List<Cliente> listarUsuarios() {
        return clienteService.listarTodos();
    }

    //POST
    @PostMapping
    public ResponseEntity<Cliente> criarUsuario(@RequestBody Cliente usuario) {
        Cliente novoUsuario = clienteService.salvar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarUsuario(@PathVariable Long id, @RequestBody Cliente usuario) {
        Cliente usuarioAtualizado = clienteService.atualizar(usuario, id);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.ok().build();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarUsuarioPorId(@PathVariable Long id) {
        Cliente usuario = clienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return ResponseEntity.ok(usuario);
    }

    //OPTIONS
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(@PathVariable Long id) {
        boolean usuarioExiste = clienteService.buscarPorId(id).isPresent();
        if (!usuarioExiste) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizarParcialmenteUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Cliente usuarioAtualizado = clienteService.atualizarParcialmente(id, updates);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //HEAD
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> consultarUsuarioHead(@PathVariable Long id) {
        Optional<Cliente> usuario = clienteService.buscarPorId(id);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();  // Retorna 404 Not Found
        } else {
            return ResponseEntity.ok().build();  // Retorna 200 OK se encontrado
        }
    }


}
