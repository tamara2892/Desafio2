package desafio4.demo.model.repositories;

import desafio4.demo.model.entities.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity,Integer> {
    public boolean existsByNombre(String nombre);
    public boolean existsByCelular(Integer celular);


}
