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
import br.com.fiap.QualyaMec.models.Instituto;
import br.com.fiap.QualyaMec.repository.InstitutoRepository;
import br.com.fiap.QualyaMec.repository.SolicitacaoDoacaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/Instituto")
public class InstitutoController {

    Logger log = LoggerFactory.getLogger(InstitutoController.class);

    @Autowired
    InstitutoRepository institutoRepository;
    
    @Autowired
    SolicitacaoDoacaoRepository solicitacaoDoacaoRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
        var instituto = (busca == null) ?
        institutoRepository.findAll(pageable):
        institutoRepository.findByNomeContaining(busca, pageable);

        return assembler.toModel(instituto.map(Instituto::toEntityModel));
    }


    @PostMapping
    public ResponseEntity<EntityModel<Instituto>> create(
        @RequestBody @Valid Instituto instituto,
        BindingResult result
        ){
        log.info("cadastrando o instituto: " + instituto);
        institutoRepository.save(instituto);
        instituto.setSolicitacaoDoacao(solicitacaoDoacaoRepository.findById(instituto.getSolicitacaoDoacao().getId()).get());
        return ResponseEntity
        .created(instituto.toEntityModel().getRequiredLink("self").toUri())
        .body(instituto.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Instituto> show(
        @PathVariable Long id
        ){
        log.info("Buscando Instituto: " + id);
        return getInstituto(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Instituto> destroy(
        @PathVariable Long id
        ){
        log.info("Apagando Ong: " + id);
        institutoRepository.delete(getInstituto(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Instituto> update(
        @PathVariable Long id,
        @RequestBody @Valid Instituto instituto
        ){
        log.info("Editando Instituto: " + id);
        getInstituto(id);
        instituto.setId(id);
        institutoRepository.save(instituto);
        return ResponseEntity.ok(instituto);
        }

        private Instituto getInstituto(Long id) {
            return institutoRepository.findById(id).orElseThrow(
                () -> new RestNotFoundException("Instituto não encontrado"));
        }
}

    

