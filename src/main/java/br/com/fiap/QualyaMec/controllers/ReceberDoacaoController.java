package br.com.fiap.QualyaMec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.QualyaMec.exceptions.RestNotFoundException;
import br.com.fiap.QualyaMec.models.ReceberDoacao;
import br.com.fiap.QualyaMec.repository.ReceberDoacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/ReceberDoacao")
@Slf4j
public class ReceberDoacaoController {

    @Autowired
    ReceberDoacaoRepository repository;

    @GetMapping
    public List<ReceberDoacao> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<ReceberDoacao> create(
            @RequestBody @Valid ReceberDoacao receberDoacao,
            BindingResult result
        ){
        repository.save(receberDoacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(receberDoacao);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceberDoacao> show(@PathVariable Long id){
        
        return ResponseEntity.ok(getReceberDoacao(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReceberDoacao> destroy(@PathVariable Long id){
        repository.delete(getReceberDoacao(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ReceberDoacao> update(
        @PathVariable Long id,
        @RequestBody @Valid ReceberDoacao receberDoacao
    ){
        getReceberDoacao(id);
        receberDoacao.setId(id);
        repository.save(receberDoacao);
        return ResponseEntity.ok(receberDoacao);
    }

    private ReceberDoacao getReceberDoacao(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Recebimento de doações não encontrato"));
    }
    
}