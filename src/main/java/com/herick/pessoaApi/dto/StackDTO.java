package com.herick.pessoaApi.dto;

import java.util.Set;

public class StackDTO {

  private String stack;

  public StackDTO() {}

  public StackDTO(long id, String stack) {
    this.stack = stack;
  }


  public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }

}
