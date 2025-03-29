package envios.internacional.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    private Long id;
    private String fechaEnvio;
    private String fechaEntrega;
    private Usuario usuario;
    private Ubicacion ubicacion;
    private EstadoEnvio estadoEnvio;
}
