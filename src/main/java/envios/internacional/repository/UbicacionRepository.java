package envios.internacional.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import envios.internacional.model.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long>{
    
}
