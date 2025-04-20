package envios.internacional.service;

import java.util.List;

import envios.internacional.model.EstadoEnvio;
import envios.internacional.exception.EstadoEnvioFoundException;
import envios.internacional.repository.EstadoEnvioRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@Service
public class EstadoEnvioService {

    @Autowired
    private EstadoEnvioRepository repositorio;


    public List<EstadoEnvio> listarEstadoEnvio(){
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public EstadoEnvio obtenerEstadoEnvioId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new EstadoEnvioFoundException(id));
    }

    // guardar estado envio
    public EstadoEnvio guardarEstadoEnvio(EstadoEnvio estadoEnvio) {
        if (repositorio.existsById(estadoEnvio.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + estadoEnvio.getId());
        }
        return repositorio.save(estadoEnvio);
    }

    // actualizar  estado envio por id
    public EstadoEnvio actualizarEstadoEnvio(Long id, EstadoEnvio EstEnvio) {
        EstadoEnvio envioEstEncontrado = repositorio.findById(id).orElseThrow(() -> new EstadoEnvioFoundException(id));

        envioEstEncontrado.setNombre(EstEnvio.getNombre());

        return repositorio.save(envioEstEncontrado);
    }

    // eliminar un  estado envio
    public void eliminarEstEnvio(Long id) {
        EstadoEnvio estEnvioEncontrado = repositorio.findById(id).orElseThrow(() -> new EstadoEnvioFoundException(id));
        repositorio.delete(estEnvioEncontrado);
    }   
    
}
