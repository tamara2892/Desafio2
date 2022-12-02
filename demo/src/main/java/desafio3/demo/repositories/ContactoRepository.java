package desafio3.demo.repositories;

import desafio3.demo.entities.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity,Integer> {
    ContactoEntity findByNombre(String nombre);
    ContactoEntity findByCelular(String celular);
}