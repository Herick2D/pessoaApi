package com.herick.pessoaApi.controller;

import com.herick.pessoaApi.dto.PessoaDTO;
import com.herick.pessoaApi.model.Pessoa;
import com.herick.pessoaApi.repository.PessoaRepository;
import com.herick.pessoaApi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private PessoaRepository pessoaRepository;
  @Autowired
  private PessoaService pessoaService;

  private boolean isPessoaInvalida(Pessoa pessoa) {
    return pessoa.getNome() == null || pessoa.getNome().isEmpty() ||
        pessoa.getApelido() == null || pessoa.getApelido().isEmpty() ||
        pessoa.getNascimento() == null || pessoa.getNascimento().isEmpty() ||
        pessoa.getStack() == null || pessoa.getStack().isEmpty();
  }

  @PostMapping
  public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDTO pessoaDTO) {
    Pessoa pessoa = new Pessoa(pessoaDTO.getApelido(), pessoaDTO.getNome(), pessoaDTO.getNascimento(), pessoaDTO.getStacks());
    if (isPessoaInvalida(pessoa)) {
      return ResponseEntity.unprocessableEntity().body(pessoa);
    }
    pessoaRepository.save(pessoa);
    return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Pessoa>> getAllPessoas() {
    List<Pessoa> pessoas = pessoaService.findAll();
    return new ResponseEntity<>(pessoas, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
    Pessoa pessoa = pessoaService.findById(id);
    if (pessoa == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(pessoa, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
    Pessoa pessoa = pessoaService.findById(id);
    if (pessoa == null) {
      return ResponseEntity.notFound().build();
    }

    if (
        pessoaDTO.getApelido() == null || pessoaDTO.getApelido().isEmpty() ||
        pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty() ||
        pessoaDTO.getNascimento() == null || pessoaDTO.getNascimento().isEmpty() ||
        pessoaDTO.getStacks() == null || pessoaDTO.getStacks().isEmpty()) {
      return ResponseEntity.unprocessableEntity().body(pessoa);
    }
    Pessoa pessoaExiste = pessoaService.findById(id);
    if (pessoaExiste != null) {
      pessoaExiste.setApelido(pessoaDTO.getApelido());
      pessoaExiste.setNome(pessoaDTO.getNome());
      pessoaExiste.setNascimento(pessoaDTO.getNascimento());
      pessoaExiste.setStack(pessoaDTO.getStacks());
      pessoaRepository.save(pessoaExiste);
      return new ResponseEntity<>(pessoaExiste, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build(); // ERRO: Faltava um retorno caso pessoaExiste fosse null.
  }

//  @GetMapping(params = "t")
//  public ResponseEntity<List<Pessoa>> searchTermo(@RequestParam(name = "t", required = false) String termo) {
//    if (termo == null || termo.isEmpty()) {
//      return ResponseEntity.badRequest().build();
//    }
//
//    List<Pessoa> pessoas = pessoaService.searchTermo(termo);
//
//    return new ResponseEntity<>(pessoas, HttpStatus.OK);
//  }

}
