package com.herick.pessoaApi.dto;

import java.util.Set;

public class StackDTO {

  private long id;
  private String stack;
  private Set<PessoaDTO> pessoas;

  public StackDTO() {}

  public StackDTO(long id, String stack, Set<PessoaDTO> pessoas) {
    this.id = id;
    this.stack = stack;
    this.pessoas = pessoas;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }

  public Set<PessoaDTO> getPessoas() {
    return pessoas;
  }

  public void setPessoas(Set<PessoaDTO> pessoas) {
    this.pessoas = pessoas;
  }
}
