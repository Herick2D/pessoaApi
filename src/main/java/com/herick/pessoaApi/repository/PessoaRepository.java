package com.herick.pessoaApi.repository;

import com.herick.pessoaApi.model.Pessoa;
import com.herick.pessoaApi.model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  Pessoa findByApelido(String apelido);
  Pessoa findByNome(String nome);
  Pessoa findByStacks(Stack stack);
//  List<Pessoa> findByTermo(String apelido, String nome, String stack);
}
