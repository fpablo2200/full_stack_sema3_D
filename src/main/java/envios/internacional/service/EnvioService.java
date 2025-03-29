package envios.internacional.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import envios.internacional.model.*;

import org.springframework.stereotype.Service;

@Service
public class EnvioService {
    private final List<Envio> envios = new ArrayList<>();
    
    public EnvioService(){
        envios.add(new Envio(1L,"05/02/2025", "10/02/2025" , 
                new Usuario(3L,"Javiera Vega", "5668915-k" , "vegaj@gmail.com", "9558436"),
                new Ubicacion(2L,"Mirasol", 2855 , "Chile", "Independencia"),
                new EstadoEnvio(4L,"Recepcionado")));

        envios.add(new Envio(2L,"17/02/2025", "18/02/2025" , 
                new Usuario(4L,"Miguel Iglesia", "21010984-6" , "Imiguel_21@gmail.com", "97668123"),
                new Ubicacion(5L,"Del sur", 9875 , "Chile", "Maipu"),
                new EstadoEnvio(4L,"Recepcionado")));

        envios.add(new Envio(3L,"30/02/2025", "01/03/2025" , 
                new Usuario(1L,"Juan Manuel", "18875707-3" , "jm.188@gmail.com", "9558412233"),
                new Ubicacion(1L,"Diagonal", 1955 , "Chile", "Santiago Centro"),
                new EstadoEnvio(5L,"Cancelado")));

        envios.add(new Envio(5L,"25/03/2025", null , 
                new Usuario(4L,"Miguel Iglesia", "21010984-6" , "Imiguel_21@gmail.com", "97668123"),
                new Ubicacion(4L,"Camino agricola", 0125 , "Chile", "San juaquin"),
                new EstadoEnvio(3L,"Viajando")));

    }

    public List<Envio> obtenerEnvios(){
        return envios;
    }

    public Optional<Envio> obtenerEnvioId(Long id){
        return envios.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    
}
