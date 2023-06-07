package br.com.fiap.QualyMec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.QualyMec.models.Instituto;


public interface InstitutoRepository extends JpaRepository<Instituto, Long>{
    Page<Instituto> findByNomeInstitutoContaining(String busca, Pageable pageable);

}
