package com.Api_java.desafio2.service;

import com.Api_java.desafio2.domain.ContactoDTO;
import com.Api_java.desafio2.domain.MensajeDTO;
import com.Api_java.desafio2.mappers.ContactoMapper;
import com.Api_java.desafio2.repositories.ContactoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactoService {
    private final ContactoRepository contactoRepository;
    private final ContactoMapper contactoMapper;

    public ContactoService(ContactoRepository contactoRepository, ContactoMapper contactoMapper) {
        this.contactoRepository = contactoRepository;
        this.contactoMapper = contactoMapper;
    }

    public List<ContactoDTO> findAll(){
        return contactoRepository
                .findAll()
                .stream()
                .map(contactoMapper::contactoEntityToContactoDTO)
                .collect(Collectors.toList());
    }

    public MensajeDTO add(ContactoDTO contactoDTO){
        return Optional
                .ofNullable(contactoDTO)
                .map(contactoMapper::contactoDTOToContactoEntity)
                .map(contactoRepository::save)
                .map(contactoMapper::contactoEntityToMensajeDTO)
                .orElse(new MensajeDTO());
    }

    public ContactoDTO findOne(Integer id){
        return Optional
                .ofNullable(id)
                .map(ID ->contactoRepository.findById(id).get())
                .map(contactoMapper::contactoEntityToContactoDTO)
                .orElse(new ContactoDTO());

    }

}

