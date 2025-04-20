package envios.internacional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstadoEnvioFoundException extends RuntimeException {
    
    public EstadoEnvioFoundException(Long id){
        super("No existe el estado envio con id: "+ id);
    }
}
