package desafio4.demo.service;

import desafio4.demo.exceptions.kinds.ContactoExistente;
import desafio4.demo.exceptions.kinds.ContactoInexistente;
import desafio4.demo.model.domain.contactoDTO.ContactoAddDTO;
import desafio4.demo.model.domain.contactoDTO.ContactoReadDTO;
import desafio4.demo.model.domain.MensajeDTO;
import desafio4.demo.model.domain.direccionDTO.DireccionAddDTO;
import desafio4.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio4.demo.model.entities.ContactoEntity;
import desafio4.demo.model.entities.DireccionEntity;
import desafio4.demo.model.mappers.ContactoMapper;
import desafio4.demo.model.mappers.DireccionMapper;
import desafio4.demo.model.repositories.ContactoRepository;
import desafio4.demo.model.repositories.DireccionRepository;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ContactoService {
    private final ContactoRepository contactoRepository;
    private final DireccionRepository direccionRepository;
    private final ContactoMapper contactoMapper;
    private final DireccionMapper direccionMapper;

    public ContactoService(ContactoRepository contactoRepository, DireccionRepository direccionRepository, ContactoMapper contactoMapper, DireccionMapper direccionMapper) {
        this.contactoRepository = contactoRepository;
        this.direccionRepository = direccionRepository;
        this.contactoMapper = contactoMapper;
        this.direccionMapper = direccionMapper;
    }

    public MensajeDTO add(ContactoAddDTO contactoAddDTO) {
        DireccionAddDTO direccionAddDTO = contactoAddDTO.getDireccion();
        DireccionEntity direccionEntity = direccionMapper.DireccionAddDTOToDireccionEntity(direccionAddDTO);
        direccionRepository.save(direccionEntity);

        DireccionReadDTO direccionReadDTO = direccionMapper.DireccionEntityToDireccionReadDTO(direccionEntity);

        if (contactoRepository.existsByNombre(contactoAddDTO.getNombre())) {
            throw new ContactoExistente("El nombre ya está utilizado.");
        } else if (contactoRepository.existsByCelular(contactoAddDTO.getCelular())) {
            throw new ContactoExistente("El número ya está utilizado.");
        } else {
            return Optional
                    .ofNullable(contactoAddDTO)
                    .map(contactoAddDTO1 -> contactoMapper.ContactoAddDTOToContactoEntity(contactoAddDTO1, direccionReadDTO))
                    .map(contactoAddDTO1 -> contactoRepository.save(contactoAddDTO1))
                    .map(contactoAddDTO1 -> contactoMapper.ContactoEntityToContactoReadDTO(contactoAddDTO1))
                    .map(entity -> contactoMapper.toMensaje(entity))
                    .orElse(new MensajeDTO());

        }
    }

    public List<ContactoReadDTO> findAll() {
        return contactoRepository
                .findAll()
                .stream()
                .map(contacto -> contactoMapper.ContactoEntityToContactoReadDTO(contacto))
                .collect(Collectors.toList());
    }

    public ContactoReadDTO findById(Integer contactoId) {
        return contactoRepository
                .findById(contactoId)
                .map(contacto -> contactoMapper.ContactoEntityToContactoReadDTO(contacto))
                .orElseThrow(() -> new ContactoInexistente("No se encuentra a la persona"));
    }



}
