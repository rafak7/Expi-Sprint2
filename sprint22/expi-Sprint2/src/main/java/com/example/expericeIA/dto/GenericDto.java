package com.example.expericeIA.dto;

import com.example.expericeIA.exception.CommitException;
import com.example.expericeIA.exception.IdNaoEncontradoException;

public interface GenericDto<T,K> {

    T salvar(T entidade);

    void remover(K id) throws IdNaoEncontradoException;

    T buscar(K id) throws IdNaoEncontradoException;

    void commit() throws CommitException;
    void atualizar(T entidade);

}
