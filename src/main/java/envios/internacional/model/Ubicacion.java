package envios.internacional.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private Long id;

    @NotBlank(message = "La direccion no puede estar vacío")
    @Size(min = 1, max = 100, message = "LA direccion debe tener entre 1 y 100 caracteres")
    private String direccion;

    @Min(value = 1, message = "La numeracion debe ser mayor a 1")
    @Max(value = 99999, message = "La numeracion debe ser menor a 99999")
    private int numeracion;

    @NotBlank(message = "El Pais no puede estar vacío")
    @Size(min = 1, max = 100, message = "El Pais debe tener entre 1 y 100 caracteres")
    private String pais;

    @NotBlank(message = "La Comuna no puede estar vacío")
    @Size(min = 1, max = 100, message = "La Comuna debe tener entre 1 y 100 caracteres")
    private String comuna;
}
