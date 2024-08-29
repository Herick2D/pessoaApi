package com.herick.pessoaApi.repository;

import com.herick.pessoaApi.model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StackRepository extends JpaRepository<Stack, Long> {
  Stack findByStack(String stack);
}
