package envios.internacional.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private Long id;

    @NotBlank(message = "La fecha de envío no puede estar vacía")
    @Size(min = 1, max = 20, message = "rango de caracteres entre 1 y 20 caracteres")
    private String fechaEnvio;

    @Size(min = 1, max = 20, message = "rango de caracteres entre 1 y 20 caracteres")
    private String fechaEntrega;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    @Positive(message = "El ID del usuario debe ser positivo")
    private int idUsuario;

    @NotNull(message = "El ID de la ubicación no puede ser nulo")
    @Positive(message = "El ID de la ubicación debe ser positivo")
    private int idUbicacion;

    @NotNull(message = "El ID estado envío no puede ser nulo")
    @Positive(message = "El ID estado envío debe ser positivo")
    private int idEstadoEnvio;
}
