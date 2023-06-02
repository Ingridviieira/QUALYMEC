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
import br.com.fiap.QualyaMec.models.SolicitacaoDoacao;
import br.com.fiap.QualyaMec.repository.SolicitacaoDoacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/SolicitacaoDoacao")
@Slf4j

public class SolicitacaoDoacaoController {
    
    @Autowired
    SolicitacaoDoacaoRepository repository;

    @Autowired
    SolicitacaoDoacao solicitacaoDoacao;

    @GetMapping
    public List<SolicitacaoDoacao> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<SolicitacaoDoacao> create(
            @RequestBody @Valid SolicitacaoDoacao solicitacaoDoacao,
            BindingResult result
        ){
        repository.save(solicitacaoDoacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoDoacao);
    }

    @GetMapping("{id}")
    public ResponseEntity<SolicitacaoDoacao> show(@PathVariable Long id){
        
        return ResponseEntity.ok(getSolicitacaoDoacao(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SolicitacaoDoacao> destroy(@PathVariable Long id){
        repository.delete(getSolicitacaoDoacao(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<SolicitacaoDoacao> update(
        @PathVariable Long id,
        @RequestBody @Valid SolicitacaoDoacao solicitacaoDoacao
    ){
        getSolicitacaoDoacao(id);
        solicitacaoDoacao.setId(id);
        repository.save(solicitacaoDoacao);
        return ResponseEntity.ok(solicitacaoDoacao);
    }

    private SolicitacaoDoacao getSolicitacaoDoacao(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Solicitacoes não encontradas"));
    }
    
}