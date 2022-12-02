package com.Api_java.desafio2.repositories;

import com.Api_java.desafio2.entities.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity,Integer> {

}