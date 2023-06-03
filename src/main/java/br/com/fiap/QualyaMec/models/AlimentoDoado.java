package br.com.fiap.QualyaMec.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Doador doador;

    public AlimentoDoado(Long id, String string, String string2, long l){
        this.id = id;
    }

    

}
