package envios.internacional.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import envios.internacional.model.EstadoEnvio;

public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvio, Long>{
    
}
