package com.knex.desafioknex.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.knex.desafioknex.dto.DeputadoDTO;
import com.knex.desafioknex.model.Deputado;

@Repository

public interface DeputadoRepository extends JpaRepository<Deputado, Long>{

    Collection<DeputadoDTO> findByUfContainingIgnoreCase(String uf);

    Optional<Deputado> findByCpf(String cpf);

    List<Deputado> findByUfIgnoreCase(String uf);

}
