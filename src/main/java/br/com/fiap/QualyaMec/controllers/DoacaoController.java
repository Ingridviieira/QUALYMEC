package br.com.fiap.QualyaMec.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.QualyaMec.exceptions.RestNotFoundException;
import br.com.fiap.QualyaMec.models.Doacao;
import br.com.fiap.QualyaMec.repository.DoacaoRepository;
import br.com.fiap.QualyaMec.repository.ReceberDoacaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/Doacao")
public class DoacaoController {

    Logger log = LoggerFactory.getLogger(DoacaoController.class);

    @Autowired
    DoacaoRepository doacaorepository;

    @Autowired
    ReceberDoacaoRepository receberDoacaoRepository;
    
    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
        var doacao = (busca == null) ?
        doacaorepository.findAll(pageable):
        doacaorepository.findByNomeContaining(busca, pageable);

        return assembler.toModel(doacao.map(Doacao::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Doacao>> create(
        @RequestBody @Valid Doacao doacao,
        BindingResult result
        ){
        log.info("cadastrando as doacoes: " + doacao);
        doacaorepository.save(doacao);
        doacao.setReceberDoacao(receberDoacaoRepository.findById(doacao.getReceberDoacao().getId()).get());
        return ResponseEntity
        .created(doacao.toEntityModel().getRequiredLink("self").toUri())
        .body(doacao.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Doacao> show(
        @PathVariable Long id
        ){
        log.info("Buscando Doacao: " + id);
        return getDoacao(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Doacao> destroy(
        @PathVariable Long id
        ){
        log.info("Apagando Doacao: " + id);
        doacaorepository.delete(getDoacao(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Doacao> update(
        @PathVariable Long id,
        @RequestBody @Valid Doacao doacao){
        log.info("Editando Doacao: " + id);
        getDoacao(id);
        doacao.setId(id);
        doacaorepository.save(doacao);
        return ResponseEntity.ok(doacao);
        }

        private Doacao getDoacao(Long id) {
            return doacaorepository.findById(id).orElseThrow(
                () -> new RestNotFoundException("Doacao não encontrado"));
        }
}

