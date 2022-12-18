package desafio6.demo.service;

import desafio6.demo.exceptions.kinds.ContactoExistente;
import desafio6.demo.exceptions.kinds.ContactoInexistente;
import desafio6.demo.model.domain.MensajeDTO;
import desafio6.demo.model.domain.contactoDTO.ContactoAddDTO;
import desafio6.demo.model.domain.contactoDTO.ContactoReadDTO;
import desafio6.demo.model.domain.contactoDTO.ListaContactosDTO;
import desafio6.demo.model.domain.direccionDTO.DireccionAddDTO;
import desafio6.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio6.demo.model.entities.DireccionEntity;
import desafio6.demo.model.mappers.ContactoMapper;
import desafio6.demo.model.mappers.DireccionMapper;
import desafio6.demo.model.repositories.ContactoRepository;
import desafio6.demo.model.repositories.DireccionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final DireccionService direccionService;

    public ContactoService(ContactoRepository contactoRepository, DireccionRepository direccionRepository, ContactoMapper contactoMapper, DireccionMapper direccionMapper, DireccionService direccionService) {
        this.contactoRepository = contactoRepository;
        this.direccionRepository = direccionRepository;
        this.contactoMapper = contactoMapper;
        this.direccionMapper = direccionMapper;
        this.direccionService = direccionService;
    }

    public List<ContactoReadDTO> findAll() {
        return contactoRepository
                .findAll()
                .stream()
                .map(contacto -> contactoMapper.ContactoEntityToContactoReadDTO(contacto))
                .collect(Collectors.toList());
    }

    public MensajeDTO add(ContactoAddDTO contactoAddDTO) {
        DireccionAddDTO direccionAddDTO = contactoAddDTO.getDireccion();
        DireccionEntity direccionEntity = direccionMapper.DireccionAddDTOToDireccionEntity(direccionAddDTO);

        if (contactoRepository.existsByNombre(contactoAddDTO.getNombre())) {
            throw new ContactoExistente("El nombre ya está utilizado.");
        } else if (contactoRepository.existsByCelular(contactoAddDTO.getCelular())) {
            throw new ContactoExistente("El número ya está utilizado.");
        } else if (direccionRepository.existsByCalleAndNumero(direccionEntity.getCalle(), direccionEntity.getNumero())) {
            DireccionReadDTO direccionEncontrada =
                    direccionMapper.DireccionEntityToDireccionReadDTO(
                            direccionRepository.findByCalleAndNumero(direccionEntity.getCalle(), direccionEntity.getNumero()));

            return Optional
                    .ofNullable(contactoAddDTO)
                    .map(contactoAddDTO1 -> contactoMapper.ContactoAddDTOToContactoEntity(contactoAddDTO1, direccionEncontrada))
                    .map(contactoAddDTO1 -> contactoRepository.save(contactoAddDTO1))
                    .map(contactoAddDTO1 -> contactoMapper.ContactoEntityToContactoReadDTO(contactoAddDTO1))
                    .map(entity -> contactoMapper.toMensaje(entity))
                    .orElse(new MensajeDTO());
        } else {
            direccionRepository.save(direccionEntity);
            DireccionReadDTO direccionReadDTO = direccionMapper.DireccionEntityToDireccionReadDTO(direccionEntity);

            return Optional
                    .ofNullable(contactoAddDTO)
                    .map(contactoAddDTO1 -> contactoMapper.ContactoAddDTOToContactoEntity(contactoAddDTO1, direccionReadDTO))
                    .map(contactoAddDTO1 -> contactoRepository.save(contactoAddDTO1))
                    .map(contactoAddDTO1 -> contactoMapper.ContactoEntityToContactoReadDTO(contactoAddDTO1))    //.contactoToContactoReadDTO(contactoadd1))
                    .map(entity -> contactoMapper.toMensaje(entity))
                    .orElse(new MensajeDTO());
        }
    }

    public ContactoReadDTO findById(Integer contactoId) {
        return contactoRepository
                .findById(contactoId)
                .map(contacto -> contactoMapper.ContactoEntityToContactoReadDTO(contacto))
                .orElseThrow(() -> new ContactoInexistente("No se encuentra a la persona"));
    }

    public MensajeDTO delete(Integer id) {
        contactoRepository.deleteById(id);
        return new MensajeDTO(id, "Eliminado correctamente");
    }

    public Page<ContactoReadDTO> findAllWithPagitation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactoRepository.findAll(pageable)
                .map(contactoEntity -> contactoMapper.ContactoEntityToContactoReadDTO(contactoEntity));

    }

    public ListaContactosDTO findAllWithFilters(Integer page, Integer size, String nombre, String celular) {
        Pageable pageable = PageRequest.of(page, size);
        return new ListaContactosDTO(contactoRepository
                .findByNombreContainingAndCelularContaining(pageable, nombre, celular)
                .stream()
                .map(contactoEntity -> contactoMapper.ContactoEntityToContactoReadDTO(contactoEntity))
                .collect(Collectors.toList()));
    }


    public ListaContactosDTO findAllByDireccion(Integer id) {
        DireccionReadDTO direccionReadDTO = direccionService.findById(id);
        DireccionEntity direccionEntity = direccionMapper.DireccionReadDTOToDireccionEntity(direccionReadDTO);

        return new ListaContactosDTO (contactoRepository.findAllByDireccion(direccionEntity)
                .stream()
                .map(contactoEntity -> contactoMapper.ContactoEntityToContactoReadDTO(contactoEntity))
                .collect(Collectors.toList()));
    }



}
