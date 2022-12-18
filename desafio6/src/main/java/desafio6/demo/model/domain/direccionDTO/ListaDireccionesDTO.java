package desafio6.demo.model.domain.direccionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDireccionesDTO {
    List<DireccionReadDTO> direcciones;
}
