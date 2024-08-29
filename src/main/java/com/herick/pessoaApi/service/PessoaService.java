package com.herick.pessoaApi.service;

import com.herick.pessoaApi.model.Stack;
import com.herick.pessoaApi.model.Pessoa;
import com.herick.pessoaApi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;

  @Autowired
  public PessoaService(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
  }

  public Pessoa createPessoa(Pessoa pessoa) {
    return pessoaRepository.save(pessoa);
  }

  public List<Pessoa> findAll() {
    return pessoaRepository.findAll();
  }

  public Pessoa findById(Long id) {
    return pessoaRepository.findById(id).orElse(null);
  }

  public Pessoa findByApelido(String apelido) {
    return pessoaRepository.findByApelido(apelido);
  }

  public Pessoa findByNome(String nome) {
    return pessoaRepository.findByNome(nome);
  }

//  public List<Pessoa> searchTermo(String termo) {
//    return pessoaRepository.findByTermo(termo, termo, termo);
//  }


  public Pessoa findByStacks(Stack stack) {
    return pessoaRepository.findByStacks(stack);
  }

  public void deletePessoa(Long id) {
    pessoaRepository.deleteById(id);
  }
}
