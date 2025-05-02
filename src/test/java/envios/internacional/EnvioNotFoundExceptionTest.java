package envios.internacional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import envios.internacional.exception.EnvioNotFoundException;

public class EnvioNotFoundExceptionTest {
    
    @Test
    public void testExceptionMessage() {
        Long id = 99L;
        EnvioNotFoundException ex = new EnvioNotFoundException(id);

        assertEquals("No existe el envio con id: 99", ex.getMessage());
    }

    @Test
    public void testThrowsException() {
        Long id = 10L;

        EnvioNotFoundException thrown = assertThrows(
            EnvioNotFoundException.class,
            () -> { throw new EnvioNotFoundException(id); }
        );

        assertEquals("No existe el envio con id: 10", thrown.getMessage());
    }
}
