package envios.internacional;

import envios.internacional.model.Envio;
import envios.internacional.repository.EnvioRepository;
import envios.internacional.exception.EnvioNotFoundException;
import envios.internacional.service.EnvioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.Sort;

public class EnvioServiceTest {

    private EnvioRepository envioRepository;
    private EnvioService envioService;

    @BeforeEach
    public void setUp() {
        envioRepository = mock(EnvioRepository.class);
        envioService = new EnvioService(envioRepository);
    }

    @Test
    public void testObtenerTodas() {

        Envio envio1 = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);
        Envio envio2 = new Envio(2L, "01-04-2025", "19-04-2025", 2, 1, 1);

        when(envioRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(envio1, envio2));

        List<Envio> resultado = envioService.obtenerEnvios();

        assertEquals(2, resultado.size());
        assertEquals(2, resultado.get(0).getIdUsuario());
        assertEquals("01-04-2025", resultado.get(1).getFechaEnvio());
    }

    @Test
    public void testObtenerPorId_existente() {

        Envio envio = new Envio(1L, "22-04-2025", "01-05-2025", 2, 2, 1);

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envio resultado = envioService.obtenerEnvioId(1L);

        assertEquals("22-04-2025", resultado.getFechaEnvio());
        assertEquals("01-05-2025", resultado.getFechaEntrega());
        assertEquals(2, resultado.getIdUsuario());
        assertEquals(2, resultado.getIdUbicacion());
        assertEquals(1, resultado.getIdEstadoEnvio());
    }

    @Test
    public void testObtenerPorId_noExistente() {

        when(envioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EnvioNotFoundException.class, () -> {
            envioService.obtenerEnvioId(99L);
        });
    }

    @Test
    public void testGuardarEnvio_nuevo() {
        Envio envio = new Envio(1L, "22-04-2025", "01-05-2025", 2, 2, 1);

        when(envioRepository.existsById(1L)).thenReturn(false);
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.guardarEnvio(envio);

        assertEquals(envio, resultado);
        verify(envioRepository).save(envio);
    }

    @Test
    public void testGuardarEnvio_idExistente() {
        Envio envio = new Envio(1L, "22-04-2025", "01-05-2025", 2, 2, 1);

        when(envioRepository.existsById(1L)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            envioService.guardarEnvio(envio);
        });

        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    public void testActualizarEnvio_existente() {
        Envio existente = new Envio(1L, "22-04-2025", "01-05-2025", 2, 2, 1);
        Envio actualizado = new Envio(1L, "23-04-2025", "02-05-2025", 3, 3, 2);

        when(envioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(envioRepository.save(any(Envio.class))).thenAnswer(i -> i.getArguments()[0]);

        Envio resultado = envioService.actualizarEnvio(1L, actualizado);

        assertEquals("23-04-2025", resultado.getFechaEnvio());
        assertEquals("02-05-2025", resultado.getFechaEntrega());
        assertEquals(3, resultado.getIdUbicacion());
        assertEquals(3, resultado.getIdUsuario());
        assertEquals(2, resultado.getIdEstadoEnvio());
    }

    @Test
    public void testActualizarEnvio_noExistente() {
        Envio actualizado = new Envio(1L, "23-04-2025", "02-05-2025", 3, 3, 2);

        when(envioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EnvioNotFoundException.class, () -> {
            envioService.actualizarEnvio(1L, actualizado);
        });

        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    public void testEliminarEnvio_existente() {
        Envio envio = new Envio(1L, "22-04-2025", "01-05-2025", 2, 2, 1);

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        envioService.eliminarEnvio(1L);

        verify(envioRepository).delete(envio);
    }

    @Test
    public void testEliminarEnvio_noExistente() {
        when(envioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EnvioNotFoundException.class, () -> {
            envioService.eliminarEnvio(1L);
        });

        verify(envioRepository, never()).delete(any(Envio.class));
    }

}
