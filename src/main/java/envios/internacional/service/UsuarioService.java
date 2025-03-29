package envios.internacional.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import envios.internacional.model.Usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();
    
    public UsuarioService(){
        usuarios.add(new Usuario(1L,"Juan Manuel", "18875707-3" , "jm.188@gmail.com", "9558412233"));
        usuarios.add(new Usuario(2L,"Hernesto Villa", "18693139-4" , "hvilla@gmail.com", "6985559981"));
        usuarios.add(new Usuario(3L,"Javiera Vega", "5668915-k" , "vegaj@gmail.com", "9558436"));
        usuarios.add(new Usuario(4L,"Miguel Iglesia", "21010984-6" , "Imiguel_21@gmail.com", "97668123"));
        usuarios.add(new Usuario(5L,"Manuel Donoso", "16148937-9" , "DMoso@gmail.com", "68887431"));
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    public Optional<Usuario> obtenerUsuarioId(Long id){
        return usuarios.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    
}
