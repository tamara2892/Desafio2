package desafio6.demo.service;

import desafio6.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio6.demo.model.domain.direccionDTO.ListaDireccionesDTO;
import desafio6.demo.model.mappers.DireccionMapper;
import desafio6.demo.model.repositories.DireccionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DireccionService {
    private final DireccionMapper direccionMapper;

    private final DireccionRepository direccionRepository;


    public DireccionService(DireccionMapper direccionMapper, DireccionRepository direccionRepository) {
        this.direccionMapper = direccionMapper;
        this.direccionRepository = direccionRepository;

    }

    public List<DireccionReadDTO> findAll(){
        return direccionRepository
                .findAll()
                .stream()
                .map(direccionEntity -> {System.out.println();return direccionMapper.DireccionEntityToDireccionReadDTO(direccionEntity);})
                .collect(Collectors.toList());
    }

    public DireccionReadDTO findById(Integer id) {
        return direccionRepository
                .findById(id)
                .map(direccionEntity -> direccionMapper.DireccionEntityToDireccionReadDTO(direccionEntity))
                .get();
    }

    public ListaDireccionesDTO findAllWithFilters(Integer page, Integer size, String calle, String numero) {
        Pageable pageable = PageRequest.of(page, size);
        return new ListaDireccionesDTO(direccionRepository
                .findByCalleContainingAndNumeroContaining(pageable, calle, numero)
                .stream()
                .map(direccionEntity -> direccionMapper.DireccionEntityToDireccionReadDTO(direccionEntity))
                .collect(Collectors.toList()));
    }
}
