package envios.internacional.controller;
import java.util.List;

import envios.internacional.service.UsuarioService;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.model.Usuario;
import envios.internacional.hateoas.UsuarioModelAssembler;

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
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioModelAssembler assembler){
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> obtUsuario() {
           
        log.info("GET /usuario - Obteniendo todos los registros");
        List<Usuario> usuario = usuarioService.obtenerUsuarios();

        if (usuario.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Usuario>> modelos = usuario.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(CollectionModel
                .of(modelos,linkTo(methodOn(UsuarioController.class)
                .obtUsuario()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> obtUsuarioId(@PathVariable Long id){
        
        log.info("GET /usuario/{} - Obtener registro por id");
        Usuario usuario = usuarioService.obtenerUsuarioId(id);
        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarUsuario(@PathVariable Long id) {
        
        log.warn("DELETE /usuario/{} - Eliminando registro", id);
        usuarioService.eliminarUsuario(id);

        return ResponseEntity.ok(
            new ResponseWrapper<>(
                    "Envio eliminado exitosamente",
                    0,
                    null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> actualizarUsuario(@PathVariable Long id,
            @Valid @RequestBody Usuario usuarioAct) {

        log.info("PUT /usuario/{} - Actualizando registro", id);
        Usuario registro = usuarioService.actualizarUsuario(id, usuarioAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> crearUbicacion(@Valid @RequestBody Usuario usuario) {
        
        log.info("POST /usuario - Creando registro: {}", usuario.getId());
        Usuario registro = usuarioService.guardarUsuario(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }
    
}
