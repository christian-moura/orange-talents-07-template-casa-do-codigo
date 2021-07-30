package br.com.zup.casadocodigo.estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado,Long> {

    @Query(value = "select * from estado as e where e.nome = :nome and e.pais_id = :id", nativeQuery = true)
    Optional<Estado> findEstadoByNomeAndPais(String nome, Object id);

    Estado findEstadoByNome(String nome);
}
