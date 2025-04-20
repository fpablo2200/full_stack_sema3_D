package envios.internacional.controller;
import java.util.List;

import envios.internacional.service.UbicacionService;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.model.Ubicacion;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {
    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService){
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    public ResponseEntity<?> obtUbicacion() {
        List<Ubicacion> ubicaciones = ubicacionService.obtenerUbicaciones();

        if (ubicaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicacion no encontrada");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        ubicaciones.size(),
                        ubicaciones));
    }



    @GetMapping("/{id}")
    public Ubicacion obtUbicacionId(@PathVariable Long id){
        return ubicacionService.obtenerUbicacionId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarUbicacion(@PathVariable Long id) {
        ubicacionService.eliminarUbicacion(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "ubicacion eliminada exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Ubicacion>> crearUbicacion(@Valid @RequestBody Ubicacion ubicacion) {
        Ubicacion registro = ubicacionService.guardarUbicacion(ubicacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Ubicacion creada exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Ubicacion>> actualizarUbicacion(@PathVariable Long id,
            @Valid @RequestBody Ubicacion ubicacionAct) {

        Ubicacion registro = ubicacionService.actualizarUbicacion(id, ubicacionAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Ubicacion actualizada",
                        1,
                        List.of(registro)));
    }
    
}
