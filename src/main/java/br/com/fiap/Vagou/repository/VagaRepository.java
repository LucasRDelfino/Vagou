package br.com.fiap.Vagou.repository;

import br.com.fiap.Vagou.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByAtivaTrue();

    List<Vaga> findByEmpresaContainingIgnoreCase(String empresa);

    List<Vaga> findByTituloContainingIgnoreCase(String titulo);

    List<Vaga> findByLocalizacaoContainingIgnoreCase(String localizacao);

    @Query("SELECT v FROM Vaga v WHERE v.ativa = true AND " +
            "(LOWER(v.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(v.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(v.tecnologias) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<Vaga> buscarPorTermo(@Param("termo") String termo);

    List<Vaga> findByNivelAndAtivaTrue(String nivel);

    List<Vaga> findByTipoContratoAndAtivaTrue(String tipoContrato);

    @Query("SELECT v FROM Vaga v WHERE :tecnologia MEMBER OF v.tecnologias AND v.ativa = true")
    List<Vaga> findByTecnologiasContainingAndAtivaTrue(@Param("tecnologia") String tecnologia);
}