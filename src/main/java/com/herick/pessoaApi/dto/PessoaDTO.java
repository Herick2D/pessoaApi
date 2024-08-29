package com.herick.pessoaApi.dto;

import com.herick.pessoaApi.model.Stack;

import java.util.Set;

public class PessoaDTO {

  private String apelido;
  private String nome;
  private String nascimento;
  private Set<Stack> stacks;

  public PessoaDTO() {}

  public PessoaDTO(String apelido, String nome, String nascimento, Set<Stack> stacks) {
    this.apelido = apelido;
    this.nome = nome;
    this.nascimento = nascimento;
    this.stacks = stacks;
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

  public Set<Stack> getStacks() {
    return stacks;
  }

  public void setStacks(Set<Stack> stacks) {
    this.stacks = stacks;
  }
}
