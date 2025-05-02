package envios.internacional.service;

import envios.internacional.model.Envio;
import envios.internacional.repository.EnvioRepository;
import envios.internacional.exception.EnvioNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repositorio;

    public EnvioService(EnvioRepository envioRepository) {
        this.repositorio = envioRepository;
    }

    // listar todo
    public List<Envio> obtenerEnvios() {
        log.debug("Servicio: obtenerEnvios()");
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Envio obtenerEnvioId(Long id) {
        log.debug("Servicio: obtenerEnvioId({})", id);
        return repositorio.findById(id).orElseThrow(() -> new EnvioNotFoundException(id));
    }

    // guardar envio
    public Envio guardarEnvio(Envio envio) {
        log.debug("Servicio: guardarEnvio({})", envio.getId());

        if (repositorio.existsById(envio.getId())) {
            log.error("Ya existe una registro con ID {}", envio.getId());
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + envio.getId());
        }
        return repositorio.save(envio);
    }

    // actualizar envio por id
    public Envio actualizarEnvio(Long id, Envio envioAct) {
        log.debug("Servicio: actualizarEnvio({}, {})", id, envioAct.getId());
        Envio envioEncontrado = repositorio.findById(id).orElseThrow(() -> new EnvioNotFoundException(id));

        envioEncontrado.setFechaEnvio(envioAct.getFechaEnvio());
        envioEncontrado.setFechaEntrega(envioAct.getFechaEntrega());
        envioEncontrado.setIdEstadoEnvio(envioAct.getIdEstadoEnvio());
        envioEncontrado.setIdUbicacion(envioAct.getIdUbicacion());
        envioEncontrado.setIdUsuario(envioAct.getIdUsuario());

        return repositorio.save(envioEncontrado);
    }

    // eliminar un envio
    public void eliminarEnvio(Long id) {
        log.debug("Servicio: eliminarEnvio({})", id);
        Envio envioEncontrado = repositorio.findById(id).orElseThrow(() -> new EnvioNotFoundException(id));
        repositorio.delete(envioEncontrado);
    }      



    
}
