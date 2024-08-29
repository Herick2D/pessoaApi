package com.herick.pessoaApi.service;

import com.herick.pessoaApi.model.Stack;
import com.herick.pessoaApi.repository.StackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StackService {

  private final StackRepository stackRepository;

  @Autowired
  public StackService(StackRepository stackRepository) {
    this.stackRepository = stackRepository;
  }

  public Stack createStack(Stack stack) {
    return stackRepository.save(stack);
  }

  public List<Stack> getAllStacks() {
    return stackRepository.findAll();
  }

  public Optional<Stack> getStackById(Long id) {
    return stackRepository.findById(id);
  }

  public void deleteStack(Long id) {
    stackRepository.deleteById(id);
  }

  public Stack findByStack(String stack) {
    return stackRepository.findByStack(stack);
  }
}
