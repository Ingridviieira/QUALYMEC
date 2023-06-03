package br.com.fiap.QualyaMec.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.QualyaMec.controllers.DoadorController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Doador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio")
    private String nomeDoador; //Nome do doador

    private String endereco;

    private String nomeDocumento;
    
    private int nrDocumento;

    // @NotBlank @Size( max= 100, message = "o campo precisa estar com a descrição da doação e não pode estar vazio, e com no maximo 100 letras")
    // private String descricaoDoacao;
    

    @ManyToOne
    private AlimentoDoado alimentoDoado;

    public EntityModel<Doador> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(DoadorController.class).show(id)).withSelfRel(),
            linkTo(methodOn(DoadorController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(DoadorController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(DoadorController.class).show(this.getAlimentoDoado().getId())).withRel("Alimento doado")
        );
    }

    
}
