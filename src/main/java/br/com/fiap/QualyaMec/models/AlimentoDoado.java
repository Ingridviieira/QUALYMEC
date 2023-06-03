package br.com.fiap.QualyaMec.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AlimentoDoado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank (message = "Descrever o tipo de alimento doado")
    private String tipoAlimento;

    @NotBlank (message = "Descrever a quantidade de alimento doado")
    private String qtdAlimento;

    public AlimentoDoado(Long id){
        this.id = id;
    }

    

}
