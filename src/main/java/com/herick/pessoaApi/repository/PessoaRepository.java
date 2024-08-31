package com.herick.pessoaApi.repository;

import com.herick.pessoaApi.model.Pessoa;
import com.herick.pessoaApi.model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  Pessoa findByApelido(String apelido);
  Pessoa findByNome(String nome);
  Pessoa findByStacks(Stack stack);

  @Query(value = "SELECT DISTINCT p.* FROM pessoas p " +
      "INNER JOIN stacks_pessoas sp ON p.id = sp.pessoa_id " +
      "INNER JOIN stacks s ON sp.stack_id = s.id " +
      "WHERE p.nome LIKE %:termo% " +
      "OR p.apelido LIKE %:termo% " +
      "OR s.stack LIKE %:termo% ", nativeQuery = true)
  List<Pessoa> findByTermo(String termo);
}