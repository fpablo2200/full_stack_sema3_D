package envios.internacional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnvioNotFoundException extends RuntimeException {
    
    public EnvioNotFoundException(Long id){
        super("No existe el envio con id: "+ id);
    }
}
