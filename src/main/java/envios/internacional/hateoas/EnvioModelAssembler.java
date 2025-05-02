package envios.internacional.hateoas;

import envios.internacional.controller.EnvioController;
import envios.internacional.model.Envio;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {
        
    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(
            envio, 
            linkTo(methodOn(EnvioController.class)
                .obtEnvios())
                .withRel("all"),

            linkTo(methodOn(EnvioController.class)
                .obtEnvioId(envio.getId()))
                .withSelfRel(),

            linkTo(methodOn(EnvioController.class)
                .eliminarEnvio(envio.getId()))
                .withRel("delete"),

            linkTo(methodOn(EnvioController.class)
                .actualizarEnvio(envio.getId(), null))
                .withRel("update")
        );
    }
}
