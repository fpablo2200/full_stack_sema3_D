package envios.internacional;

import envios.internacional.model.Envio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.*;
import java.util.Set;

public class EnvioModelTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEnvioValido() {
        Envio envio = new Envio(1L, "2025-03-17", "2025-03-31", 2, 3, 1);
        Set<ConstraintViolation<Envio>> violations = validator.validate(envio);

        assertTrue(violations.isEmpty(), "No debería haber errores de validación");
    }

    @Test
    public void testEnvioInvalidoPorCamposNulos() {
        Envio envio = new Envio(null, "", null, -1, 0, -2);
        Set<ConstraintViolation<Envio>> violations = validator.validate(envio);

        // Muestra los errores para depuración
        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));

        assertEquals(6, violations.size()); // 6 errores esperados por las restricciones
    }

    @Test
    public void testEnvioConFechasFueraDeRango() {
        Envio envio = new Envio(1L, "fecha muy larga que excede la cantidad de caracteres", "otra fecha larga que falla por largo del string", 1, 1, 1);
        Set<ConstraintViolation<Envio>> violations = validator.validate(envio);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("fechaEnvio")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("fechaEntrega")));
    }
}
