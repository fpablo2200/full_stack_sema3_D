package envios.internacional.controller;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import envios.internacional.service.EstadoEnvioService;
import envios.internacional.model.EstadoEnvio;
import envios.internacional.model.ResponseWrapper;

@RestController
@RequestMapping("/estadoenvio")
public class EstadoEnvioController {
    private final EstadoEnvioService estadoEnvioService;

    public EstadoEnvioController(EstadoEnvioService estadoEnvioService){
        this.estadoEnvioService = estadoEnvioService;
    }

    @GetMapping
    public ResponseEntity<?> obtEstados(){
        List<EstadoEnvio> estenvios = estadoEnvioService.listarEstadoEnvio();

               if (estenvios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado envios no encontrado");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        estenvios.size(),
                        estenvios));
    }


    @GetMapping("/{id}")
    public EstadoEnvio obtEstadoId(@PathVariable Long id){
        return estadoEnvioService.obtenerEstadoEnvioId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEstEnvio(@PathVariable Long id) {
        estadoEnvioService.eliminarEstEnvio(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Estado envio eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<EstadoEnvio>> crearEnvio(@Valid @RequestBody EstadoEnvio estEnvio) {
        EstadoEnvio registro = estadoEnvioService.guardarEstadoEnvio(estEnvio);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Estado envio creado exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<EstadoEnvio>> actualizarEstEnvio(@PathVariable Long id,
            @Valid @RequestBody EstadoEnvio estEnvioAct) {

            EstadoEnvio registro = estadoEnvioService.actualizarEstadoEnvio(id, estEnvioAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envio actualizado",
                        1,
                        List.of(registro)));
    }

    
}
