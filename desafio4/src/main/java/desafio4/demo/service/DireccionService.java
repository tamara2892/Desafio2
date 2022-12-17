package desafio4.demo.service;

import desafio4.demo.model.domain.contactoDTO.ContactoAddDTO;
import desafio4.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio4.demo.model.mappers.DireccionMapper;
import desafio4.demo.model.repositories.DireccionRepository;
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

//*    public DireccionReadDTO add(DireccionReadDTO contactoAddDTO){
//
//        return Optional
//                .ofNullable(contactoAddDTO)
//                .map(contactoAddDTO1 -> direccionMapper.DireccionReadDTOToDireccionEntity(contactoAddDTO1))
//                .map(direccionRepository::save)
//                .map(direccionMapper::DireccionEntityToDireccionReadDTO)
//                .orElse(new DireccionReadDTO());
//    }

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
}
