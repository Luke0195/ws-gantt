package br.com.plannermanager.utils.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class HttpUtil {

    private HttpUtil(){}

    public static  int getStatusCode(HttpStatus httpStatus){
        return httpStatus.value();
    }

    public static String getUriFromRequest(HttpServletRequest request){
        return request.getRequestURI();
    }

    public static URI getUriFromObject(Object object){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object).toUri();
    }

    public static <T,K> ResponseEntity<K> getCreatedResponse(T object, K payload){
        URI uri = getUriFromObject(object);
        return ResponseEntity.created(uri).body(payload);
    }

    public static<K> ResponseEntity<K> getBadRequestResponse( K payload){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }

}
