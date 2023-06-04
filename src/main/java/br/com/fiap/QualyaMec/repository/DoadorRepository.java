package br.com.fiap.QualyaMec.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.QualyaMec.models.Doador;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
    Page<Doador> findByNomeDoadorContaining(String busca, Pageable pageable);

    
}
