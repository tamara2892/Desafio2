package desafio5.demo.model.domain.contactoDTO;

import desafio5.demo.model.domain.direccionDTO.DireccionAddDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoAddDTO {
    private String nombre;
    private String celular;
    private DireccionAddDTO direccion;
}
