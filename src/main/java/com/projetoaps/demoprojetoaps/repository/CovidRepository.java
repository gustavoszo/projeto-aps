package com.projetoaps.demoprojetoaps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetoaps.demoprojetoaps.entity.Covid;
import com.projetoaps.demoprojetoaps.entity.Local;

public interface CovidRepository extends JpaRepository<Covid, Long> {

    Page<Covid> findAllByLocal(Pageable page, Local local);

    Optional<Covid> findByLocalAndSemana(Local local, Integer semana);

    @Query("SELECT SUM(c.qtObitos) FROM Covid c WHERE c.local = ?1")
    Long findTotalQtObitosByLocal(Local local);

    @Query("SELECT SUM(c.qtCasos) FROM Covid c WHERE c.local = ?1")
    Long findTotalQtCasosByLocal(Local local);

    @Query("SELECT c.semana FROM Covid c WHERE c.local = ?1")
    List<Integer> findAllSemanaByLocal(Local local);

    @Query("SELECT MAX(c.semana) FROM Covid c")
    Integer findFirstByOrderBySemanaDesc();

    @Query("SELECT MIN(c.semana) FROM Covid c")
    Integer findFirstByOrderBySemanaAsc();

    // FiltrosPeriodo
    @Query("SELECT c FROM Covid c WHERE c.local = ?1 AND c.semana >= ?2 AND c.semana <= ?3")
    Page<Covid> findAllByLocalAndPeriodo(Pageable page, Local local, Integer comeco, Integer fim);

    @Query("SELECT c.semana FROM Covid c WHERE c.semana >= ?1 AND c.semana <= ?2")
    List<Integer> findSemanaByPeriodo(Integer comeco, Integer fim);

    @Query("SELECT SUM(c.qtObitos) FROM Covid c WHERE c.local = ?1 AND c.semana >= ?2 AND c.semana <= ?3")
    Long findTotalQtObitosByLocalAndPeriodo(Local local, Integer comeco, Integer fim);

    @Query("SELECT SUM(c.qtCasos) FROM Covid c WHERE c.local = ?1 AND c.semana >= ?2 AND c.semana <= ?3")
    Long findTotalQtCasosByLocalAndPeriodo(Local local, Integer comeco, Integer fim);

    @Query("SELECT c.local, SUM(c.qtCasos) FROM Covid c GROUP BY c.local")
    List<Object[]> findAllQtCasos();

    @Query("SELECT c.local, SUM(c.qtObitos) FROM Covid c GROUP BY c.local")
    List<Object[]> findAllQtObitos();

    // Valores dashboard
    @Query("SELECT SUM(c.qtObitos) from Covid c")
    Long getTotalObitos();

    @Query("SELECT SUM(c.qtCasos) from Covid c")
    Long getTotalCasos();
 
}
