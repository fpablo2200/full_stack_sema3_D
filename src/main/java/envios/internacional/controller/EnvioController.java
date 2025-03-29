package envios.internacional.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import envios.internacional.service.EnvioService;
import envios.internacional.model.Envio;

@RestController
@RequestMapping("/envio")
public class EnvioController {
    private final EnvioService envioService;

    public EnvioController(EnvioService envioService){
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> obtEnvios(){
        return envioService.obtenerEnvios();
    }

    @GetMapping("/{id}")
    public Optional<Envio> obtEnvioId(@PathVariable Long id){
        return envioService.obtenerEnvioId(id);
    }
    
}
