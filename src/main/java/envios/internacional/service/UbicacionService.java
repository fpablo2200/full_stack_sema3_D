package envios.internacional.service;
import java.util.List;

import envios.internacional.model.Ubicacion;
import envios.internacional.repository.UbicacionRepository;
import envios.internacional.exception.UbicacionNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository repositorio;

    // listar todo
    public List<Ubicacion> obtenerUbicaciones() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Ubicacion obtenerUbicacionId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new UbicacionNotFoundException(id));
    }

    // guardar 
    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {
        if (repositorio.existsById(ubicacion.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + ubicacion.getId());
        }
        return repositorio.save(ubicacion);
    }

    // actualizar por id
    public Ubicacion actualizarUbicacion(Long id, Ubicacion ubicacionAct) {
        Ubicacion ubicacionEncontrado = repositorio.findById(id).orElseThrow(() -> new UbicacionNotFoundException(id));

        ubicacionEncontrado.setDireccion(ubicacionAct.getDireccion());
        ubicacionEncontrado.setNumeracion(ubicacionAct.getNumeracion());
        ubicacionEncontrado.setPais(ubicacionAct.getPais());
        ubicacionEncontrado.setComuna(ubicacionAct.getComuna());

        return repositorio.save(ubicacionEncontrado);
    }

    // eliminar 
    public void eliminarUbicacion(Long id) {
        Ubicacion ubicacionEncontrado = repositorio.findById(id).orElseThrow(() -> new UbicacionNotFoundException(id));
        repositorio.delete(ubicacionEncontrado);
    }    
    
}
