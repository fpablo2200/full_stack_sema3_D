package envios.internacional.controller;
import java.util.List;

import envios.internacional.model.Envio;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.service.EnvioService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envio")
public class EnvioController {
    private final EnvioService envioService;

    public EnvioController(EnvioService envioService){
        this.envioService = envioService;
    }

    @GetMapping
    public ResponseEntity<?> obtEnvios() {
        List<Envio> envios = envioService.obtenerEnvios();

        if (envios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envio no encontrada");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        envios.size(),
                        envios));
    }



    @GetMapping("/{id}")
    public Envio obtEnvioId(@PathVariable Long id){
        return envioService.obtenerEnvioId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEnvio(@PathVariable Long id) {
        envioService.eliminarEnvio(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envio eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Envio>> crearEnvio(@Valid @RequestBody Envio envio) {
        Envio registro = envioService.guardarEnvio(envio);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Envio creada exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Envio>> actualizarEnvio(@PathVariable Long id,
            @Valid @RequestBody Envio envioAct) {

        Envio registro = envioService.actualizarEnvio(id, envioAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envio actualizado",
                        1,
                        List.of(registro)));
    }



    
}
