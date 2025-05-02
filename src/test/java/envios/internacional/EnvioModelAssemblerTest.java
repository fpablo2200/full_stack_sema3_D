package envios.internacional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import envios.internacional.hateoas.EnvioModelAssembler;
import envios.internacional.model.Envio;

public class EnvioModelAssemblerTest {
    
    private EnvioModelAssembler assembler;

    @BeforeEach
    public void setUp() {
        assembler = new EnvioModelAssembler();
    }

    @Test
    public void testToModel() {
        // Given
        Envio envio = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);

        // When
        EntityModel<Envio> model = assembler.toModel(envio);

        // Then
        assertNotNull(model.getContent());
        assertEquals(envio, model.getContent());

        // Verifica enlaces
        assertTrue(model.getLinks().hasLink("self"));
        assertTrue(model.getLinks().hasLink("all"));
        assertTrue(model.getLinks().hasLink("delete"));
        assertTrue(model.getLinks().hasLink("update"));

        // Puedes imprimir para depurar
        model.getLinks().forEach(link -> System.out.println(link.getRel() + " -> " + link.getHref()));
    }
}
