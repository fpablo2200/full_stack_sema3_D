package envios.internacional.hateoas;

import envios.internacional.controller.UsuarioController;
import envios.internacional.model.Usuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(
            usuario, 

            linkTo(methodOn(UsuarioController.class)
                    .obtUsuario())
                    .withRel("all"),

            linkTo(methodOn(UsuarioController.class)
                    .obtUsuarioId(usuario.getId()))
                    .withSelfRel(),

            linkTo(methodOn(UsuarioController.class)
                    .eliminarUsuario(usuario.getId()))
                    .withRel("delete"),

            linkTo(methodOn(UsuarioController.class)
                    .actualizarUsuario(usuario.getId(), null))
                    .withRel("update")

        );
    }
}
