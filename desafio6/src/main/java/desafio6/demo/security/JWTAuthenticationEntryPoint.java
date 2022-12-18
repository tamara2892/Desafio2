package desafio6.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 3798723588865329956L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        Map<String, Object> responseMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        response.setStatus(401);
        responseMap.put("mensaje", "Unauthorized");
        responseMap.put("codigo", 401);
        response.setHeader("content-type", "application/json");
        String responseMsg = mapper.writeValueAsString(responseMap);
        response.getWriter().write(responseMsg);
    }
}
