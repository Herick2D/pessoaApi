package com.herick.pessoaApi.dto;

import com.herick.pessoaApi.model.Pessoa;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PessoaDTO {

  private Long id;
  private String apelido;
  private String nome;
  private String nascimento;
  private Set<String> stack;

  public PessoaDTO() {}

  public PessoaDTO(Long id, String apelido, String nome, String nascimento, Set<String> stacks) {
    this.id = id;
    this.apelido = apelido;
    this.nome = nome;
    this.nascimento = nascimento;
    this.stack = stacks;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getApelido() {
    return apelido;
  }

  public void setApelido(String apelido) {
    this.apelido = apelido;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNascimento() {
    return nascimento;
  }

  public void setNascimento(String nascimento) {
    this.nascimento = nascimento;
  }

  public Set<String> getStack() {
    return stack;
  }

  public void setStack(Set<String> stack) {
    this.stack = stack;
  }

  public static PessoaDTO mapToPessoaDTO(Pessoa pessoa) {
    return new PessoaDTO(pessoa.getId(), pessoa.getApelido(), pessoa.getNome(), pessoa.getNascimento(),
        pessoa.getStack().stream()
            .map(stack -> stack.getStack())
            .collect(Collectors.toSet())
      );
  }

  public static List<PessoaDTO> mapToPessoaDTOList(List<Pessoa> pessoas) {
    return pessoas.stream()
        .map(PessoaDTO::mapToPessoaDTO)
        .collect(Collectors.toList());
  }
}
