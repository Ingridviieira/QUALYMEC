package br.com.fiap.QualyMec.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.QualyMec.controllers.InstitutoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Instituto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio")
    private String nomeInstituto;

    
    private int nrRegistro;

    @NotBlank (message = "Descrever o numero de registro da empresa")
    private String endereco;

    @ManyToOne
    private SolicitacaoDoacao solicitacaoDoacao;

    public EntityModel<Instituto> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(InstitutoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(InstitutoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(InstitutoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(InstitutoController.class).show(this.getSolicitacaoDoacao().getId())).withRel("Pedir doacao")
        );
    }
    
}
