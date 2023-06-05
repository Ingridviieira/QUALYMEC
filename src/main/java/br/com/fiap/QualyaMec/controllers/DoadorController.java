package br.com.fiap.QualyaMec.controllers;

import org.springdoc.core.annotations.ParameterObject;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/doador")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "doador")
public class DoadorController {

    @Autowired
    DoadorRepository doadoerepository;

    @Autowired
    AlimentoDoadoRepository alimentoDoadoRepository;
    
    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        var doador = (busca == null) ?
        doadoerepository.findAll(pageable):
        doadoerepository.findByNomeDoadorContaining(busca, pageable);

        return assembler.toModel(doador.map(Doador::toEntityModel)); //HAL
    }
    
    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "o doador foi cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "os dados enviados são inválidos")
    })
    public ResponseEntity<EntityModel<Doador>> create(
            @RequestBody @Valid Doador doador,
            BindingResult result) {
        log.info("cadastrando doador: " + doador);
        doadoerepository.save(doador);
        doador.setAlimentoDoado(alimentoDoadoRepository.findById(doador.getAlimentoDoado().getId()).get());
        return ResponseEntity
            .created(doador.toEntityModel().getRequiredLink("self").toUri())
            .body(doador.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes do doador",
        description = "Retornar os dados do doador de acordo com o id informado no path"
    )
    public EntityModel<Doador> show(@PathVariable Long id) {
        log.info("buscando doador: " + id);
        return getDoador(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Doador> destroy(@PathVariable Long id) {
        log.info("apagando doador: " + id);
        doadoerepository.delete(getDoador(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Doador>> update(
            @PathVariable Long id,
            @RequestBody @Valid Doador doador) {
        log.info("atualizando o doador: " + id);
        getDoador(id);
        doador.setId(id);
        doadoerepository.save(doador);
        return ResponseEntity.ok(doador.toEntityModel());
    }

    private Doador getDoador(Long id) {
        return doadoerepository.findById(id).orElseThrow(
                () -> new RestNotFoundException("doador não encontrado"));
    }
}