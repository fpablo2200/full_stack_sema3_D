package envios.internacional.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import envios.internacional.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
