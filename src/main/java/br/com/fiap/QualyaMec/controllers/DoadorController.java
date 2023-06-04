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
import br.com.fiap.QualyaMec.models.Doador;
import br.com.fiap.QualyaMec.repository.AlimentoDoadoRepository;
import br.com.fiap.QualyaMec.repository.DoadorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/Doacao")
public class DoadorController {

    Logger log = LoggerFactory.getLogger(DoadorController.class);

    @Autowired
    DoadorRepository doacaorepository;

    @Autowired
    AlimentoDoadoRepository alimentoDoadoRepository;
    
    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
        var doacao = (busca == null) ?
        doacaorepository.findAll(pageable):
        doacaorepository.findByNomeDoadorContaining(busca, pageable);

        return assembler.toModel(doacao.map(Doador::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Doador>> create(
        @RequestBody @Valid Doador doacao,
        BindingResult result
        ){
        log.info("cadastrando as doacoes: " + doacao);
        doacaorepository.save(doacao);
        doacao.setAlimentoDoado(alimentoDoadoRepository.findById(doacao.getAlimentoDoado().getId()).get());
        return ResponseEntity
        .created(doacao.toEntityModel().getRequiredLink("self").toUri())
        .body(doacao.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Doador> show(
        @PathVariable Long id
        ){
        log.info("Buscando Doacao: " + id);
        return getDoacao(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Doador> destroy(
        @PathVariable Long id
        ){
        log.info("Apagando Doacao: " + id);
        doacaorepository.delete(getDoacao(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Doador> update(
        @PathVariable Long id,
        @RequestBody @Valid Doador doacao){
        log.info("Editando Doacao: " + id);
        getDoacao(id);
        doacao.setId(id);
        doacaorepository.save(doacao);
        return ResponseEntity.ok(doacao);
        }

        private Doador getDoacao(Long id) {
            return doacaorepository.findById(id).orElseThrow(
                () -> new RestNotFoundException("Doacao não encontrado"));
        }
}

