package com.keviny.egg.interpreter;

import java.util.ArrayList;
import java.util.List;

// This class acts as a wrapper for a list of tokens and scanner errors
public class TokensWrapper {
  private List<Token> tokens = new ArrayList<>();
  private List<EggError> errors = new ArrayList<>();
  
  // Getters & setters
  public List<Token> getTokens() {
    return tokens;
  }

  public void setTokens(List<Token> tokens) {
    this.tokens = tokens;
  }

  public List<EggError> getErrors() {
    return errors;
  }

  public void setErrors(List<EggError> errors) {
    this.errors = errors;
  }
}
