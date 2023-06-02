package br.com.fiap.QualyaMec.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.QualyaMec.models.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    Page<Doacao> findByNomeContaining(String busca, Pageable pageable);

    
}
