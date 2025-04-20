package envios.internacional.controller;
import java.util.List;

import envios.internacional.service.UsuarioService;
import envios.internacional.model.ResponseWrapper;
import envios.internacional.model.Usuario;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<?> obtUsuario() {
        List<Usuario> usuario = usuarioService.obtenerUsuarios();

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrada");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        usuario.size(),
                        usuario));
    }



    @GetMapping("/{id}")
    public Usuario obtUsuarioId(@PathVariable Long id){
        return usuarioService.obtenerUsuarioId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Usuario eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Usuario>> crearUbicacion(@Valid @RequestBody Usuario usuario) {
        Usuario registro = usuarioService.guardarUsuario(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Usuario creado exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Usuario>> actualizarUsuario(@PathVariable Long id,
            @Valid @RequestBody Usuario usuarioAct) {

        Usuario registro = usuarioService.actualizarUsuario(id, usuarioAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Usuario actualizada",
                        1,
                        List.of(registro)));
    }
    
}
