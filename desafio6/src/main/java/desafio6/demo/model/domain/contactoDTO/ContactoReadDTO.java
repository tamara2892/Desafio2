package desafio6.demo.model.domain.contactoDTO;

import desafio6.demo.model.domain.direccionDTO.DireccionReadDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoReadDTO {
    private Integer id;
    private String nombre;
    private String celular;
    private DireccionReadDTO direccion;
}
