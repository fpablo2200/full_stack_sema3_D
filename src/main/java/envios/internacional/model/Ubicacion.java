package envios.internacional.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ubicacion {
    private Long id;
    private String direccion;
    private int numeracion;
    private String Pais;
    private String Comuna;
}
