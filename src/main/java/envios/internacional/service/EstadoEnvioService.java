package envios.internacional.service;

import java.util.List;

import envios.internacional.model.EstadoEnvio;
import envios.internacional.exception.EstadoEnvioFoundException;
import envios.internacional.repository.EstadoEnvioRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadoEnvioService {

    @Autowired
    private EstadoEnvioRepository repositorio;

    public EstadoEnvioService(EstadoEnvioRepository estadoEnvioRepository){
        this.repositorio = estadoEnvioRepository;
    }

    public List<EstadoEnvio> listarEstadoEnvio(){
        log.debug("Servicio: listarEstadoEnvio()");
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public EstadoEnvio obtenerEstadoEnvioId(Long id) {
        log.debug("Servicio: obtenerEstadoEnvioId({})", id);
        return repositorio.findById(id).orElseThrow(() -> new EstadoEnvioFoundException(id));
    }

    // guardar estado envio
    public EstadoEnvio guardarEstadoEnvio(EstadoEnvio estadoEnvio) {
        log.debug("Servicio: guardarEstadoEnvio({})", estadoEnvio.getId());
        if (repositorio.existsById(estadoEnvio.getId())) {
            log.error("Ya existe una registro con ID {}", estadoEnvio.getId());
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + estadoEnvio.getId());
        }
        return repositorio.save(estadoEnvio);
    }

    // actualizar  estado envio por id
    public EstadoEnvio actualizarEstadoEnvio(Long id, EstadoEnvio EstEnvio) {
        log.debug("Servicio: actualizarEstadoEnvio({}, {})", id, EstEnvio.getId());
        EstadoEnvio envioEstEncontrado = repositorio.findById(id).orElseThrow(() -> new EstadoEnvioFoundException(id));

        envioEstEncontrado.setNombre(EstEnvio.getNombre());

        return repositorio.save(envioEstEncontrado);
    }

    // eliminar un  estado envio
    public void eliminarEstEnvio(Long id) {
        log.debug("Servicio: eliminarEstEnvio({})", id);
        EstadoEnvio estEnvioEncontrado = repositorio.findById(id).orElseThrow(() -> new EstadoEnvioFoundException(id));
        repositorio.delete(estEnvioEncontrado);
    }   
    
}
