package com.herick.pessoaApi.model;

import com.herick.pessoaApi.dto.StackDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pessoas")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 32)
  private String apelido;

  @Column(nullable = false, unique = true, length = 100)
  private String nome;

  @Column(nullable = false)
  private String nascimento;

  @ManyToMany
  @JoinTable(
      name = "stacks_pessoas",
      joinColumns = @JoinColumn(name = "pessoa_id"),
      inverseJoinColumns = @JoinColumn(name = "stack_id")
  )
  private Set<Stack> stacks; // cada elemento dentro do array deve ser de no máximo 32 caracteres, trabalhar nesse requisito na versão final.

  // Construtor

  public Pessoa() {

  }

  public Pessoa(String apelido, String nome, String nascimento, Set<Stack> stack) {
    this.apelido = apelido;
    this.nome = nome;
    this.nascimento = nascimento;
    this.stacks = stack;
  }

  // Getters e Setters
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

  public Set<Stack> getStack() {
    return stacks;
  }

  public void setStack(Set<Stack> stack) {
    this.stacks = stack;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pessoa pessoa = (Pessoa) o;
    return Objects.equals(id, pessoa.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
