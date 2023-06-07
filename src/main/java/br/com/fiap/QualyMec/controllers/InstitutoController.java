package br.com.fiap.QualyMec.controllers;

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

import br.com.fiap.QualyMec.exceptions.RestNotFoundException;
import br.com.fiap.QualyMec.models.Instituto;
import br.com.fiap.QualyMec.repository.InstitutoRepository;
import br.com.fiap.QualyMec.repository.SolicitacaoDoacaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/instituto")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "instituto")
public class InstitutoController {

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
        institutoRepository.findByNomeInstitutoContaining(busca, pageable);

        return assembler.toModel(instituto.map(Instituto::toEntityModel));
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "o instituto foi cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "os dados enviados são inválidos")
    })
    public ResponseEntity<EntityModel<Instituto>> create(
            @RequestBody @Valid Instituto instituto,
            BindingResult result) {
        log.info("cadastrando instituto: " + instituto);
        institutoRepository.save(instituto);
        instituto.setSolicitacaoDoacao(solicitacaoDoacaoRepository.findById(instituto.getSolicitacaoDoacao().getId()).get());
        return ResponseEntity
            .created(instituto.toEntityModel().getRequiredLink("self").toUri())
            .body(instituto.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes do Instituto",
        description = "Retornar os dados da instituto de acordo com o id informado no path"
    )
    public EntityModel<Instituto> show(@PathVariable Long id) {
        log.info("buscando instituto: " + id);
        return getInstituto(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Instituto> destroy(@PathVariable Long id) {
        log.info("apagando instituto: " + id);
        institutoRepository.delete(getInstituto(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Instituto>> update(
            @PathVariable Long id,
            @RequestBody @Valid Instituto instituto) {
        log.info("atualizando instituto: " + id);
        getInstituto(id);
        instituto.setId(id);
        institutoRepository.save(instituto);
        return ResponseEntity.ok(instituto.toEntityModel());
    }

        private Instituto getInstituto(Long id) {
            return institutoRepository.findById(id).orElseThrow(
                () -> new RestNotFoundException("Instituto não encontrado"));
        }
}

    

