package br.com.tiagoiwamoto.iwtrecibos.config.filter;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/03/2022 | 06:48
 */

import br.com.tiagoiwamoto.iwtrecibos.config.rest.ApiResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | SignatureException | MalformedJwtException exception){
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            String path = request.getRequestURI();

            ApiResponseDto<String> apiResponse = ApiResponseDto.of(status.name(), path, exception.getMessage());

            response.setStatus(status.value());
            response.setContentType("application/json");
            response.getWriter().write(Objects.requireNonNull(this.convertObjectToJson(apiResponse)));
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if(Objects.isNull(object)){
            return null;
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);
    }
}
