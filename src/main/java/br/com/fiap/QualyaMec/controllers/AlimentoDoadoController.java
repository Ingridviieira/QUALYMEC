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
import br.com.fiap.QualyaMec.models.AlimentoDoado;
import br.com.fiap.QualyaMec.repository.AlimentoDoadoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/AlimentoDoado")
public class AlimentoDoadoController {
    
    @Autowired
    AlimentoDoadoRepository repository;

    @GetMapping
    public List<AlimentoDoado> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<AlimentoDoado> create(
        @RequestBody @Valid AlimentoDoado alimentoDoado,
        BindingResult result){
        repository.save(alimentoDoado);
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentoDoado);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlimentoDoado> show(@PathVariable Long id){
        
        return ResponseEntity.ok(getAlimentoDoado(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AlimentoDoado> destroy(@PathVariable Long id){
        repository.delete(getAlimentoDoado(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<AlimentoDoado> update(
        @PathVariable Long id,
        @RequestBody @Valid AlimentoDoado alimentoDoado
    ){
        getAlimentoDoado(id);
        alimentoDoado.setId(id);
        repository.save(alimentoDoado);
        return ResponseEntity.ok(alimentoDoado);
    }

    private AlimentoDoado getAlimentoDoado(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Alimento não encontrado"));
    }

}

