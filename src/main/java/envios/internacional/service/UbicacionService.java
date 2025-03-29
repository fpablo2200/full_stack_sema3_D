package envios.internacional.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import envios.internacional.model.Ubicacion;

import org.springframework.stereotype.Service;

@Service
public class UbicacionService {
    private final List<Ubicacion> ubicacions = new ArrayList<>();
    
    public UbicacionService(){
        ubicacions.add(new Ubicacion(1L,"Diagonal", 1955 , "Chile", "Santiago Centro"));
        ubicacions.add(new Ubicacion(2L,"Mirasol", 2855 , "Chile", "Independencia"));
        ubicacions.add(new Ubicacion(3L,"Avenida henriquez", 255 , "Chile", "Independencia"));
        ubicacions.add(new Ubicacion(4L,"Camino agricola", 0125 , "Chile", "San juaquin"));
        ubicacions.add(new Ubicacion(5L,"Del sur", 9875 , "Chile", "Maipu"));
    }

    public List<Ubicacion> obtenerUbicacions(){
        return ubicacions;
    }

    public Optional<Ubicacion> obtenerUbicacionId(Long id){
        return ubicacions.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    
}
