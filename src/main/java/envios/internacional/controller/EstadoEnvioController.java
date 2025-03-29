package envios.internacional.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import envios.internacional.service.EstadoEnvioService;
import envios.internacional.model.EstadoEnvio;

@RestController
@RequestMapping("/estadoenvio")
public class EstadoEnvioController {
    private final EstadoEnvioService estadoEnvioService;

    public EstadoEnvioController(EstadoEnvioService estadoEnvioService){
        this.estadoEnvioService = estadoEnvioService;
    }

    @GetMapping
    public List<EstadoEnvio> obtEstados(){
        return estadoEnvioService.obtenerEstados();
    }

    @GetMapping("/{id}")
    public Optional<EstadoEnvio> obtEstadoId(@PathVariable Long id){
        return estadoEnvioService.obtenerEstadoId(id);
    }
    
}
