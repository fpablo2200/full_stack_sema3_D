package envios.internacional.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import envios.internacional.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long>{
    
}
