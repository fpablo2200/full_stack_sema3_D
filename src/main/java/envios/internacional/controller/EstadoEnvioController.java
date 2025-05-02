package envios.internacional.controller;
import java.util.List;

import envios.internacional.service.EstadoEnvioService;
import envios.internacional.model.EstadoEnvio;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.hateoas.EstadoEnvioModelAssembler;

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
@RequestMapping("/estadoenvio")
public class EstadoEnvioController {

    private final EstadoEnvioService estadoEnvioService;
    private final EstadoEnvioModelAssembler assembler;

    public EstadoEnvioController(EstadoEnvioService estadoEnvioService, EstadoEnvioModelAssembler assembler){
        this.estadoEnvioService = estadoEnvioService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<EstadoEnvio>>> obtEstados(){
    
        log.info("GET /estadoenvio - Obteniendo todos los registros");
        List<EstadoEnvio> estenvios = estadoEnvioService.listarEstadoEnvio();

        if (estenvios.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<EstadoEnvio>> modelos = estenvios.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(EstadoEnvioController.class).obtEstados()).withSelfRel()));
    }

    // obtener todos los registros
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EstadoEnvio>>  obtEstadoId(@PathVariable Long id){
       
        log.info("GET /estadoenvio/{} - Obtener registro por id");
        EstadoEnvio estadoenvi = estadoEnvioService.obtenerEstadoEnvioId(id);
        return ResponseEntity.ok(assembler.toModel(estadoenvi));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEstEnvio(@PathVariable Long id) {
        
        log.warn("DELETE /estadoenvio/{} - Eliminando registro", id);
        estadoEnvioService.eliminarEstEnvio(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Estado envio eliminado exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EstadoEnvio>> actualizarEstEnvio(@PathVariable Long id,
            @Valid @RequestBody EstadoEnvio estEnvioAct) {

            log.info("PUT /estadoenvio/{} - Actualizando registro", id);
            EstadoEnvio registro = estadoEnvioService.actualizarEstadoEnvio(id, estEnvioAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<EstadoEnvio>> crearEnvio(@Valid @RequestBody EstadoEnvio estEnvio) {
        
        log.info("POST /estadoenvio - Creando registro: {}", estEnvio.getId());
        EstadoEnvio registro = estadoEnvioService.guardarEstadoEnvio(estEnvio);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }
}
