package br.com.fiap.QualyaMec.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.QualyaMec.controllers.DoacaoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio")
    private String nome;

    @NotBlank @Size( max= 100, message = "o campo precisa estar com a descrição da doação e não pode estar vazio, e com no maximo 100 letras")
    private String descricaoDoacao;
    
    @NotBlank (message = "Descrever o tipo de alimento doado")
    private String tipoAlimento;

    private String qtdAlimento;

    @ManyToOne
    private ReceberDoacao receberDoacao;

    public EntityModel<Doacao> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(DoacaoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(DoacaoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(DoacaoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(DoacaoController.class).show(this.getReceberDoacao().getId())).withRel("Receber doação")
        );
    }

    public Doacao getReceberDoacao() {
        return null;
    }

    
}
