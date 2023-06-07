package br.com.fiap.QualyMec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.QualyMec.models.AlimentoDoado;

public interface AlimentoDoadoRepository extends JpaRepository<AlimentoDoado, Long>{
    
}
