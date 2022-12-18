package desafio6.demo.model.mappers;


import desafio6.demo.model.domain.MensajeDTO;
import desafio6.demo.model.domain.contactoDTO.ContactoAddDTO;
import desafio6.demo.model.domain.contactoDTO.ContactoReadDTO;
import desafio6.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio6.demo.model.entities.ContactoEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContactoMapper {

    private final DireccionMapper direccionMapper;

    public ContactoMapper(DireccionMapper direccionMapper) {
        this.direccionMapper = direccionMapper;
    }

    public ContactoEntity ContactoAddDTOToContactoEntity(ContactoAddDTO contactoAddDTO, DireccionReadDTO direccionRead) {
        ContactoEntity contactoEntity = new ContactoEntity();
        contactoEntity.setNombre(contactoAddDTO.getNombre());
        contactoEntity.setCelular(contactoAddDTO.getCelular());
        contactoEntity.setDireccion(direccionMapper.DireccionReadDTOToDireccionEntity(direccionRead));
        return contactoEntity;
    }

    // de ContactoRead a Contacto Entidad
    public ContactoEntity ContactoReadDTOToContactoEntity(ContactoReadDTO contactoReadDTO) {
        ContactoEntity contactoEntity = new ContactoEntity();
        contactoEntity.setId(contactoReadDTO.getId());
        contactoEntity.setNombre(contactoReadDTO.getNombre());
        contactoEntity.setCelular(contactoReadDTO.getCelular());
        contactoEntity.setDireccion(direccionMapper.DireccionReadDTOToDireccionEntity(contactoReadDTO.getDireccion()));
        return contactoEntity;
    }

    // de Contacto Entidad a ContactoRead
    public ContactoReadDTO ContactoEntityToContactoReadDTO(ContactoEntity contactoEntity) {
        return Optional
                .ofNullable(contactoEntity)
                .map(entity -> new ContactoReadDTO(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getCelular(),
                        direccionMapper.DireccionEntityToDireccionReadDTO(entity.getDireccion())))
                .orElse(new ContactoReadDTO());
    }

    // transforma a mensaje
    public MensajeDTO toMensaje(ContactoReadDTO contactoDTO) {
        return Optional
                .ofNullable(contactoDTO)
                .map(contacto -> new MensajeDTO(contacto.getId(), "Añadido Exitosamente"))
                .orElse(new MensajeDTO());
    }

}
