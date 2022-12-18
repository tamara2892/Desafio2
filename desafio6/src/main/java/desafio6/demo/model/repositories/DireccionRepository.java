package desafio6.demo.model.repositories;

import desafio6.demo.model.entities.DireccionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity,Integer> {

    public boolean existsByCalleAndNumero(String calle, String numero);

    public DireccionEntity findByCalleAndNumero(String calle, String numero);

    Page<DireccionEntity> findByCalleContainingAndNumeroContaining(Pageable pageable, String calle, String numero);
}
