package desafio6.demo.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
    public Integer id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)")
    public String username;

    @Column(name = "password", columnDefinition = "VARCHAR(80)")
    public String password;
}
