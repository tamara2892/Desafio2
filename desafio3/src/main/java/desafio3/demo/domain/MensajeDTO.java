package desafio3.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private Integer id;
    private String mensaje = "Añadido Exitosamente";

    public MensajeDTO(Integer id) {
        this.id = id;
    }
}