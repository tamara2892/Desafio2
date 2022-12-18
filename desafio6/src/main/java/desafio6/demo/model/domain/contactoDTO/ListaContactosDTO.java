package desafio6.demo.model.domain.contactoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaContactosDTO {
    List<ContactoReadDTO> contactos;
}
