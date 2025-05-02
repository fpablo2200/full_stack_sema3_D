package envios.internacional;

import envios.internacional.model.Envio;
import envios.internacional.repository.EnvioRepository;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
public class EnvioRepositoryTest {
    
    @Autowired
    private EnvioRepository envioRepository;

    @Test
    public void testSaveFind() {
        Envio envio = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);

        envioRepository.save(envio);

        Optional<Envio> resultado = envioRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("17-03-2025", resultado.get().getFechaEnvio());
        assertEquals("31-03-2025", resultado.get().getFechaEntrega());
        assertEquals(2, resultado.get().getIdUsuario());
        assertEquals(2, resultado.get().getIdUbicacion());
        assertEquals(1, resultado.get().getIdEstadoEnvio());
    }

    @Test
    public void testUpdateEnvio() {
        Envio envio = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);
        envioRepository.save(envio);

        envio.setFechaEntrega("01-04-2025");
        envio.setIdEstadoEnvio(3);
        envioRepository.save(envio);

        Optional<Envio> resultado = envioRepository.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("01-04-2025", resultado.get().getFechaEntrega());
        assertEquals(3, resultado.get().getIdEstadoEnvio());
    }

    @Test
    public void testDeleteEnvio() {
        Envio envio = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);
        envioRepository.save(envio);

        envioRepository.deleteById(1L);

        Optional<Envio> resultado = envioRepository.findById(1L);
        assertFalse(resultado.isPresent());
    }

    @Test
    public void testFindAllEnvios() {
        envioRepository.save(new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1));
        envioRepository.save(new Envio(2L, "18-03-2025", "01-04-2025", 3, 3, 1));
    
        List<Envio> envios = envioRepository.findAll();
        assertEquals(2, envios.size());
    }
    
}
