package envios.internacional.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import envios.internacional.model.EstadoEnvio;

import org.springframework.stereotype.Service;

@Service
public class EstadoEnvioService {
    private final List<EstadoEnvio> estadoEnvios = new ArrayList<>();
    
    public EstadoEnvioService(){
        estadoEnvios.add(new EstadoEnvio(1L,"Enviado"));
        estadoEnvios.add(new EstadoEnvio(2L,"Aceptado"));
        estadoEnvios.add(new EstadoEnvio(3L,"Viajando"));
        estadoEnvios.add(new EstadoEnvio(4L,"Recepcionado"));
        estadoEnvios.add(new EstadoEnvio(5L,"Cancelado"));
    
    }

    public List<EstadoEnvio> obtenerEstados(){
        return estadoEnvios;
    }

    public Optional<EstadoEnvio> obtenerEstadoId(Long id){
        return estadoEnvios.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    
}
