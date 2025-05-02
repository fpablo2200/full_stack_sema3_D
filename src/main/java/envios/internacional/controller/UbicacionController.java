package envios.internacional.controller;
import java.util.List;

import envios.internacional.service.UbicacionService;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.model.Ubicacion;
import envios.internacional.hateoas.UbicacionModelAssembler;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    private final UbicacionService ubicacionService;
    private final UbicacionModelAssembler assembler;

    public UbicacionController(UbicacionService ubicacionService, UbicacionModelAssembler assembler){
        this.ubicacionService = ubicacionService;
        this.assembler = assembler;
    }

        // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Ubicacion>>> obtUbicaciones() {
        
        log.info("GET /ubicacion - Obteniendo todos los registros");
        List<Ubicacion> ubicaciones = ubicacionService.obtenerUbicaciones();

        if (ubicaciones.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Ubicacion>> modelos = ubicaciones.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(CollectionModel
                .of(modelos,linkTo(methodOn(UbicacionController.class)
                .obtUbicaciones()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Ubicacion>> obtUbicacionId(@PathVariable Long id){
        
        log.info("GET /ubicacion/{} - Obtener registro por id");
        Ubicacion ubicacion = ubicacionService.obtenerUbicacionId(id);
        return ResponseEntity.ok(assembler.toModel(ubicacion));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarUbicacion(@PathVariable Long id) {
        
        log.warn("DELETE /ubicacion/{} - Eliminando registro", id);
        ubicacionService.eliminarUbicacion(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "ubicacion eliminada exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Ubicacion>> actualizarUbicacion(@PathVariable Long id,
            @Valid @RequestBody Ubicacion ubicacionAct) {

        log.info("PUT /ubicacion/{} - Actualizando registro", id);
        Ubicacion registro = ubicacionService.actualizarUbicacion(id, ubicacionAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Ubicacion>> crearUbicacion(@Valid @RequestBody Ubicacion ubicacion) {
       
        log.info("POST /ubicacion - Creando registro: {}", ubicacion.getId());
        Ubicacion registro = ubicacionService.guardarUbicacion(ubicacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }
    
}
