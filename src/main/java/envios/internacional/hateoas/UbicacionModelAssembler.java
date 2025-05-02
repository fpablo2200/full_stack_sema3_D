package envios.internacional.hateoas;

import envios.internacional.controller.UbicacionController;
import envios.internacional.model.Ubicacion;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UbicacionModelAssembler implements RepresentationModelAssembler<Ubicacion, EntityModel<Ubicacion>> {
        
    @Override
    public EntityModel<Ubicacion> toModel(Ubicacion ubicacion) {
        return EntityModel.of(
            ubicacion, 

            linkTo(methodOn(UbicacionController.class)
                    .obtUbicaciones())
                    .withRel("all"),

            linkTo(methodOn(UbicacionController.class)
                    .obtUbicacionId(ubicacion.getId()))
                    .withSelfRel(),

            linkTo(methodOn(UbicacionController.class)
                    .eliminarUbicacion(ubicacion.getId()))
                    .withRel("delete"),
                    
            linkTo(methodOn(UbicacionController.class)
                    .actualizarUbicacion(ubicacion.getId(), null))
                    .withRel("update")
        );
    }
}
