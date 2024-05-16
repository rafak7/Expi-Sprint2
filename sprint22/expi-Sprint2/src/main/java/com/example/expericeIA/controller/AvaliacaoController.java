package com.example.expericeIA.controller;

import com.example.expericeIA.entity.Avaliacao;
import com.example.expericeIA.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/resultado")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    //GET
    @GetMapping
    public List<Avaliacao> listarResultado() {
        return avaliacaoService.listarTodos();
    }

    //POST
    @PostMapping
    public ResponseEntity<Avaliacao> criarResultado(@RequestBody Avaliacao resultado) {
        Avaliacao novoUsuario = avaliacaoService.salvar(resultado);
        return ResponseEntity.ok(novoUsuario);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarResultado(@PathVariable Long id, @RequestBody Avaliacao resultado) {
        Avaliacao usuarioAtualizado = avaliacaoService.atualizar(resultado, id);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirResultado(@PathVariable Long id) {
        avaliacaoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> consultarResultadoPorId(@PathVariable Long id) {
        Avaliacao resultado = avaliacaoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("não encontrado"));
        return ResponseEntity.ok(resultado);
    }

    //OPTIONS
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(@PathVariable Long id) {
        boolean resultadoExiste = avaliacaoService.buscarPorId(id).isPresent();
        if (!resultadoExiste) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }

    //PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarParcialmenteResultado(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Avaliacao usuarioAtualizado = avaliacaoService.atualizarParcialmente(id, updates);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //HEAD
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> consultarResultadoHead(@PathVariable Long id) {
        Optional<Avaliacao> resultado = avaliacaoService.buscarPorId(id);
        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();  // Retorna 404 Not Found
        } else {
            return ResponseEntity.ok().build();  // Retorna 200 OK se encontrado
        }
    }

}
