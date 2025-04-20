package envios.internacional.service;
import java.util.List;

import envios.internacional.model.Usuario;
import envios.internacional.repository.UsuarioRepository;
import envios.internacional.exception.UsuarioNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    // listar todo
    public List<Usuario> obtenerUsuarios() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Usuario obtenerUsuarioId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    // guardar 
    public Usuario guardarUsuario(Usuario usuario) {
        if (repositorio.existsById(usuario.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + usuario.getId());
        }
        return repositorio.save(usuario);
    }

    // actualizar por id
    public Usuario actualizarUsuario(Long id, Usuario usuarioAct) {
        Usuario usuarioEncontrado = repositorio.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

        usuarioEncontrado.setNombre(usuarioAct.getNombre());
        usuarioEncontrado.setDni(usuarioAct.getDni());
        usuarioEncontrado.setCorreo(usuarioAct.getCorreo());
        usuarioEncontrado.setTelefono(usuarioAct.getTelefono());

        return repositorio.save(usuarioEncontrado);
    }

    // eliminar 
    public void eliminarUsuario(Long id) {
        Usuario usuarioEncontrado = repositorio.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        repositorio.delete(usuarioEncontrado);
    }    
    
}
