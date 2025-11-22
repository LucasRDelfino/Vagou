package br.com.fiap.Vagou.repository;

import br.com.fiap.Vagou.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    List<Candidatura> findByVagaId(Long vagaId);

    List<Candidatura> findByEmail(String email);

    List<Candidatura> findByStatus(String status);

    List<Candidatura> findByCandidatoId(Long candidatoId);

    boolean existsByVagaIdAndCandidatoId(Long vagaId, Long candidatoId);

    @Query("SELECT c FROM Candidatura c WHERE c.candidato.id = :candidatoId AND c.status = :status")
    List<Candidatura> findByCandidatoIdAndStatus(@Param("candidatoId") Long candidatoId,
                                                 @Param("status") String status);

}