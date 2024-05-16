package com.example.expericeIA.service;

import com.example.expericeIA.entity.Avaliacao;
import com.example.expericeIA.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao salvar(Avaliacao resultado) {
        return avaliacaoRepository.save(resultado);
    }

    public void excluir(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Optional<Avaliacao> buscarPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public Avaliacao atualizar(Avaliacao avaliacao, Long id) {
            return avaliacaoRepository.findById(id)
                    .map(avaliacaoExistente -> {
                        avaliacaoExistente.setAvaliacaoNota(avaliacao.getAvaliacaoNota());
                        avaliacaoExistente.setAvaliacaoFeedback(avaliacao.getAvaliacaoFeedback());

                        return avaliacaoRepository.save(avaliacaoExistente);
                    }).orElseThrow(() -> new IllegalStateException("Avaliação com ID " + id + " não existe."));
        }

    public Avaliacao atualizarParcialmente(Long id, Map<String, Object> updates) {
        return avaliacaoRepository.findById(id)
                .map(usuarioExistente -> {
                    updates.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Avaliacao.class, key);
                        if (field != null) {
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, usuarioExistente, value);
                        }
                    });
                    return avaliacaoRepository.save(usuarioExistente);
                }).orElseThrow(() -> new IllegalStateException("Usuário com ID " + id + " não existe."));
    }

}
