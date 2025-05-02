package envios.internacional.controller;
import java.util.List;

import envios.internacional.model.Envio;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.service.EnvioService;
import envios.internacional.hateoas.EnvioModelAssembler;

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
@RequestMapping("/envio")
public class EnvioController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    public EnvioController(EnvioService envioService, EnvioModelAssembler assembler){
        this.envioService = envioService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Envio>>> obtEnvios() {
    
        log.info("GET /envio - Obteniendo todos los registros");
        List<Envio> envios = envioService.obtenerEnvios();

        if (envios.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Envio>> modelos = envios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
            linkTo(methodOn(EnvioController.class).obtEnvios()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Envio>> obtEnvioId(@PathVariable Long id){
        log.info("GET /envio/{} - Obtener registro por id");
        Envio envio = envioService.obtenerEnvioId(id);
        return ResponseEntity.ok(assembler.toModel(envio));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEnvio(@PathVariable Long id) {
        log.warn("DELETE /envio/{} - Eliminando registro", id);
        envioService.eliminarEnvio(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Envio eliminado exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Envio>> actualizarEnvio(@PathVariable Long id, @Valid @RequestBody Envio envioAct) {

        log.info("PUT /envio/{} - Actualizando registro", id);
        Envio registro = envioService.actualizarEnvio(id, envioAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Envio>> crearEnvio(@Valid @RequestBody Envio envio) {

        log.info("POST /envio - Creando registro: {}", envio.getId());
        Envio registro = envioService.guardarEnvio(envio);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }

}
