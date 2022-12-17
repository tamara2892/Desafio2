package desafio4.demo.model.repositories;

import desafio4.demo.model.entities.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity,Integer> {
}
