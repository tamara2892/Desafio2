package desafio4.demo.model.domain.contactoDTO;

import desafio4.demo.model.domain.direccionDTO.DireccionAddDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoAddDTO {
    private String nombre;
    private Integer celular;
    private DireccionAddDTO direccion;
}
