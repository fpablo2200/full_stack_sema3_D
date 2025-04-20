package envios.internacional.service;

import envios.internacional.model.Envio;
import envios.internacional.repository.EnvioRepository;
import envios.internacional.exception.EnvioNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repositorio;

    // listar todo
    public List<Envio> obtenerEnvios() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Envio obtenerEnvioId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new EnvioNotFoundException(id));
    }

    // guardar envio
    public Envio guardarEnvio(Envio envio) {
        if (repositorio.existsById(envio.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + envio.getId());
        }
        return repositorio.save(envio);
    }

    // actualizar envio por id
    public Envio actualizarEnvio(Long id, Envio envioAct) {
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
        Envio envioEncontrado = repositorio.findById(id).orElseThrow(() -> new EnvioNotFoundException(id));
        repositorio.delete(envioEncontrado);
    }      



    
}
