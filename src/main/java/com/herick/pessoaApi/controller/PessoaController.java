package com.herick.pessoaApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.herick.pessoaApi.dto.PessoaDTO;
import com.herick.pessoaApi.model.Pessoa;
import com.herick.pessoaApi.model.Stack;
import com.herick.pessoaApi.repository.PessoaRepository;
import com.herick.pessoaApi.repository.StackRepository;
import com.herick.pessoaApi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private PessoaRepository pessoaRepository;
  @Autowired
  private PessoaService pessoaService;
  @Autowired
  private StackRepository stackRepository;

  private boolean isPessoaInvalida(Pessoa pessoa) {
    return pessoa.getNome() == null || pessoa.getNome().isEmpty() ||
        pessoa.getApelido() == null || pessoa.getApelido().isEmpty() ||
        pessoa.getNascimento() == null || pessoa.getNascimento().isEmpty() ||
        pessoa.getStack() == null || pessoa.getStack().isEmpty();
  }

  private Set<Stack> saveStacks(Set<String> stacks) {
    Set<Stack> stacksSaved = new HashSet<>();
    for (String stack : stacks) {
      var stackExiste = stackRepository.findByStack(stack);
      if (stackExiste == null) {
        var stackSaved = stackRepository.save(new Stack(stack));
        stacksSaved.add(stackSaved);
      } else {
        stacksSaved.add(stackExiste);
      }
    }
    return stacksSaved;
  }

  @PostMapping
  public ResponseEntity<PessoaDTO> createPessoa(@RequestBody PessoaDTO pessoaDTO) throws JsonProcessingException {
    if (pessoaDTO.getStack() == null || pessoaDTO.getStack().isEmpty()) {
      return ResponseEntity.unprocessableEntity().build();
    }
    Set<Stack> stacks = saveStacks(pessoaDTO.getStack());
    Pessoa pessoa = new Pessoa(pessoaDTO.getApelido(), pessoaDTO.getNome(), pessoaDTO.getNascimento());
    pessoa.setStack(stacks);
    if (isPessoaInvalida(pessoa)) {
      return ResponseEntity.unprocessableEntity().body(pessoaDTO);
    }
    var pessoaSaved = pessoaRepository.save(pessoa);
    return new ResponseEntity<>(PessoaDTO.mapToPessoaDTO(pessoaSaved) , HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PessoaDTO> getPessoaById(@PathVariable Long id) {
    Pessoa pessoa = pessoaService.findById(id);
    if (pessoa == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(PessoaDTO.mapToPessoaDTO(pessoa), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) throws JsonProcessingException {
    Pessoa pessoaExiste = pessoaService.findById(id);
    if (pessoaExiste == null) {
      return ResponseEntity.notFound().build();
    }
    if (
        pessoaDTO.getApelido() == null || pessoaDTO.getApelido().isEmpty() ||
            pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty() ||
            pessoaDTO.getNascimento() == null || pessoaDTO.getNascimento().isEmpty() ||
            pessoaDTO.getStack() == null || pessoaDTO.getStack().isEmpty()) {
      return ResponseEntity.unprocessableEntity().build();
    }

    String apelidoCodificado = URLEncoder.encode(pessoaDTO.getApelido(), StandardCharsets.UTF_8).replace("+", " ");

    Set<String> stacksCodificados = pessoaDTO.getStack().stream()
            .map(stack -> URLEncoder.encode(stack).replace("+", " "))
            .collect(Collectors.toSet());

    pessoaExiste.setApelido(apelidoCodificado);
    pessoaExiste.setNome(pessoaDTO.getNome());
    pessoaExiste.setNascimento(pessoaDTO.getNascimento());
    Set<Stack> stacks = saveStacks(stacksCodificados);
    pessoaExiste.setStack(stacks);
    pessoaRepository.save(pessoaExiste);
    var response = PessoaDTO.mapToPessoaDTO(pessoaExiste);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(params = "t")
  public ResponseEntity<List<PessoaDTO>> searchTermo(@RequestParam(name = "t", required = false) String termo) {
    if (termo == null || termo.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    List<Pessoa> pessoas = pessoaRepository.findByTermo(termo);
    return new ResponseEntity<>(PessoaDTO.mapToPessoaDTOList(pessoas), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id) {
    Pessoa pessoa = pessoaService.findById(id);
    if (pessoa == null) {
      return ResponseEntity.badRequest().build();
    }
    pessoaService.deletePessoa(id);
    return ResponseEntity.noContent().build();
  }
}
