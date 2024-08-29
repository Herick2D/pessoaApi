package com.herick.pessoaApi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stacks")
public class Stack {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true, length = 32)
  private String stack;

  @ManyToMany(mappedBy = "stacks")
  private Set<Pessoa> pessoas;

  // Construtores
  public Stack() {}

  public Stack(long id, String stack, Set<Pessoa> pessoas) {
    this.id = id;
    this.stack = stack;
    this.pessoas = pessoas;
  }

  // Getters e Setters
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

  public Set<Pessoa> getPessoas() {
    return pessoas;
  }

  public void setPessoas(Set<Pessoa> pessoas) {
    this.pessoas = pessoas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stack stack = (Stack) o;
    return id == stack.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
