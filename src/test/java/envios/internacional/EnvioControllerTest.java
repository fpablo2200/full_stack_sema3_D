package envios.internacional;

import envios.internacional.controller.EnvioController;
import envios.internacional.model.Envio;
import envios.internacional.service.EnvioService;
import envios.internacional.hateoas.EnvioModelAssembler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean

    private EnvioService envioService;
    @SuppressWarnings("removal")
    @MockBean

    private EnvioModelAssembler envioAssambler;

    @Test
    @WithMockUser(username = "test", password = "qwerasdf", roles = { "ADMIN" })
    public void testObtenerPorId() throws Exception {

        Envio envio = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);

        EntityModel<Envio> envioModel = EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).obtEnvioId(1L)).withSelfRel(),
                linkTo(methodOn(EnvioController.class).eliminarEnvio(1L)).withRel("delete"),
                linkTo(methodOn(EnvioController.class).actualizarEnvio(1L, null)).withRel("update"),
                linkTo(methodOn(EnvioController.class).obtEnvios()).withRel("all"));

        when(envioService.obtenerEnvioId(1L)).thenReturn(envio);
        when(envioAssambler.toModel(envio)).thenReturn(envioModel);

        mockMvc.perform(get("/envio/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fechaEnvio").value("17-03-2025"))
                .andExpect(jsonPath("$.fechaEntrega").value("31-03-2025"))
                .andExpect(jsonPath("$.idUsuario").value(2))
                .andExpect(jsonPath("$.idUbicacion").value(2))
                .andExpect(jsonPath("$.idEstadoEnvio").value(1))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.delete.href").exists())
                .andExpect(jsonPath("$._links.update.href").exists());
    }


    @Test
    @WithMockUser(username = "test", password = "qwerasdf", roles = { "ADMIN" })
    public void testObtenerTodosLosEnvios_conResultados() throws Exception {
        Envio envio = new Envio(1L, "17-03-2025", "31-03-2025", 2, 2, 1);
        List<Envio> lista = List.of(envio);
    
        EntityModel<Envio> envioModel = EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).obtEnvioId(1L)).withSelfRel());
    
        when(envioService.obtenerEnvios()).thenReturn(lista);
        when(envioAssambler.toModel(envio)).thenReturn(envioModel);
    
        mockMvc.perform(get("/envio")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.envioList[0].id").value(1))
                .andExpect(jsonPath("$._links.self.href").exists());
    }
}
