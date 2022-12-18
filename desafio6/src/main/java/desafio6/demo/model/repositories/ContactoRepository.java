package desafio6.demo.model.repositories;

import desafio6.demo.model.entities.ContactoEntity;
import desafio6.demo.model.entities.DireccionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity,Integer> {
    public boolean existsByNombre(String nombre);
    public boolean existsByCelular(String celular);
    Page<ContactoEntity> findByNombreContainingAndCelularContaining(Pageable pageable, String nombre, String celular);
    List<ContactoEntity> findAllByDireccion(DireccionEntity direccionEntity);

}
