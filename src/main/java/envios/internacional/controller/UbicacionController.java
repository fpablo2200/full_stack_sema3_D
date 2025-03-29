package envios.internacional.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import envios.internacional.service.UbicacionService;
import envios.internacional.model.Ubicacion;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {
    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService){
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    public List<Ubicacion> obtUbicacions(){
        return ubicacionService.obtenerUbicacions();
    }

    @GetMapping("/{id}")
    public Optional<Ubicacion> obtUbicacionId(@PathVariable Long id){
        return ubicacionService.obtenerUbicacionId(id);
    }
    
}
