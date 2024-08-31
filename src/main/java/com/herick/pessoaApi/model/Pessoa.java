package com.herick.pessoaApi.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "pessoas")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 32)
  private String apelido;

  @Column(nullable = false, length = 100)
  private String nome;

  @Column(nullable = false)
  private String nascimento;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "stacks_pessoas",
      joinColumns = @JoinColumn(name = "pessoa_id"),
      inverseJoinColumns = @JoinColumn(name = "stack_id")
  )
  private Set<Stack> stacks; // cada elemento dentro do array deve ser de no máximo 32 caracteres, trabalhar nesse requisito na versão final.

  // Construtor

  public Pessoa() {

  }

  public Pessoa(String apelido, String nome, String nascimento) throws JsonProcessingException {
    this.apelido = apelido;
    this.nome = nome;
    this.nascimento = nascimento;
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

  // Equals e HashCode
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

  public void readStackDTO(Set<String> stacks) throws JsonProcessingException {
    var mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(stacks);
    Set<Stack> stack = mapper.readValue(jsonString, new TypeReference<Set<Stack>>() {});
    this.stacks = stack;
    Set<Stack> dtoSet = stacks.stream()
        .map(Stack::new) // Mapeando cada String para um StringDTO
        .collect(Collectors.toSet());
  }
}
