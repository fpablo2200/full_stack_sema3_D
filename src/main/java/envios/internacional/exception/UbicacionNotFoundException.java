package envios.internacional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UbicacionNotFoundException extends RuntimeException {
    
    public UbicacionNotFoundException(Long id){
        super("No existe el envio con id: "+ id);
    }
    
}
