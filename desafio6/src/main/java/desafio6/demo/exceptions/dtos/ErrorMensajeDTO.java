package desafio6.demo.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMensajeDTO {
    private String menssage;
    private String path;
    private String code;
}
