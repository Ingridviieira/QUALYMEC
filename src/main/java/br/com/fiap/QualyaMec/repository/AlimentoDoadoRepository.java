package br.com.fiap.QualyaMec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.QualyaMec.models.AlimentoDoado;

public interface AlimentoDoadoRepository extends JpaRepository<AlimentoDoado, Long>{
    
}
