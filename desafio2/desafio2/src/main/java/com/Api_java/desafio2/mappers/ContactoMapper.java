package com.Api_java.desafio2.mappers;


import com.Api_java.desafio2.domain.ContactoDTO;
import com.Api_java.desafio2.domain.MensajeDTO;
import com.Api_java.desafio2.entities.ContactoEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContactoMapper {
    public ContactoEntity contactoDTOToContactoEntity (ContactoDTO contactoDTO){
    ContactoEntity contactoEntity = new ContactoEntity();
    contactoEntity.setNombre(contactoDTO.getNombre());
    contactoEntity.setCelular(contactoDTO.getCelular());
    contactoEntity.setId(contactoDTO.getId());
    return contactoEntity;
    }

    public ContactoDTO contactoEntityToContactoDTO(ContactoEntity contactoEntity) {
        return Optional
                .ofNullable(contactoEntity)
                .map(entity-> new ContactoDTO(entity.getId(), entity.getNombre(), entity.getCelular()))
                .orElse(new ContactoDTO());
    }

    public MensajeDTO contactoEntityToMensajeDTO (ContactoEntity contactoEntity){
        return Optional
                .ofNullable(contactoEntity)
                .map(entity -> new MensajeDTO(entity.getId()))
                .orElse((new MensajeDTO()));
    }

}
