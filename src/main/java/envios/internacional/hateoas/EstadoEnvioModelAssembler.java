package envios.internacional.hateoas;

import envios.internacional.controller.EstadoEnvioController;
import envios.internacional.model.EstadoEnvio;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EstadoEnvioModelAssembler implements RepresentationModelAssembler<EstadoEnvio, EntityModel<EstadoEnvio>> {
        
    @Override
    public EntityModel<EstadoEnvio> toModel(EstadoEnvio estadoEnvio) {
        return EntityModel.of(
            estadoEnvio, 

            linkTo(methodOn(EstadoEnvioController.class)
                    .obtEstados())
                    .withRel("all"),
            linkTo(methodOn(EstadoEnvioController.class)
                    .obtEstadoId(estadoEnvio.getId()))
                    .withSelfRel(),

            linkTo(methodOn(EstadoEnvioController.class)
                    .eliminarEstEnvio(estadoEnvio.getId()))
                    .withRel("delete"),

            linkTo(methodOn(EstadoEnvioController.class)
                    .actualizarEstEnvio(estadoEnvio.getId(), null))
                    .withRel("update")

        );
    }
}
