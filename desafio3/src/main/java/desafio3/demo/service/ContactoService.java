package desafio3.demo.service;

import desafio3.demo.exceptions.kinds.ContactoExistente;
import desafio3.demo.exceptions.kinds.ContactoInexistente;
import desafio3.demo.model.domain.ContactoDTO;
import desafio3.demo.model.domain.MensajeDTO;
import desafio3.demo.model.entities.ContactoEntity;
import desafio3.demo.model.mappers.ContactoMapper;
import desafio3.demo.model.repositories.ContactoRepository;
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

    public ContactoDTO findById(Integer contactoId){
        return contactoRepository
                .findById(contactoId)
                .map(contactoEntity -> contactoMapper.contactoEntityToContactoDTO(contactoEntity))
                .orElseThrow(()->new ContactoInexistente("No se encuentra a la persona"));
    }

    public MensajeDTO add(ContactoDTO contactoDTO) {
        ContactoEntity contactonombre = contactoRepository.findByNombre(contactoDTO.getNombre());
        ContactoEntity contactocelular = contactoRepository.findByCelular(contactoDTO.getCelular());
        if (contactonombre == null && contactocelular == null) {
            return Optional
                    .ofNullable(contactoDTO)
                    .map(dto -> contactoMapper.contactoDTOToContactoEntity(dto))
                    .map(entity -> contactoRepository.save(entity))
                    .map(entity -> contactoMapper.contactoEntityToMensajeDTO(entity))
                    .orElse(new MensajeDTO());
        } else if (contactonombre != null) {
            throw new ContactoExistente("El nombre ya está utilizado");
        } else if (contactocelular != null) {
            throw new ContactoExistente("El número ya está utilizado");
        } else {return null;}
    }

}
