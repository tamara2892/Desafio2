package desafio5.demo.model.mappers;

import desafio5.demo.model.domain.direccionDTO.DireccionAddDTO;
import desafio5.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio5.demo.model.entities.DireccionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DireccionMapper {

    public DireccionEntity DireccionReadDTOToDireccionEntity(DireccionReadDTO direccionRead) {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setId(direccionRead.getId());
        direccionEntity.setCalle(direccionRead.getCalle());
        direccionEntity.setNumero(direccionRead.getNumero());
        return direccionEntity;
    }


    public DireccionEntity DireccionAddDTOToDireccionEntity (DireccionAddDTO direccionAddDTO) {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCalle(direccionAddDTO.getCalle());
        direccionEntity.setNumero(direccionAddDTO.getNumero());
        return direccionEntity;
    }

    public DireccionReadDTO DireccionEntityToDireccionReadDTO(DireccionEntity direccionEntity) {

        return Optional
                .ofNullable(direccionEntity)
                .map(entity -> new DireccionReadDTO(
                        entity.getId(),
                        entity.getCalle(),
                        entity.getNumero()))
                .orElse(new DireccionReadDTO());
    }

}
