package com.knex.desafioknex.repository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.knex.desafioknex.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query("SELECT SUM(d.valorLiquido) FROM Despesa d")
    BigDecimal somatorioTotalDespesas();

    @Query("SELECT SUM(d.valorLiquido) FROM Despesa d WHERE d.deputado.id = :deputadoId")
    BigDecimal somatorioPorDeputado(@Param("deputadoId") Long deputadoId);

    List<Despesa> findByDeputadoId(Long deputadoId);

}
