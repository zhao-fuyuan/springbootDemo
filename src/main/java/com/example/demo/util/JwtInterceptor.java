package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@CrossOrigin
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        if ("OPTIONS".equals(method)){

            return true;
        }
         String token = request.getHeader("accessToken");
         if (token == null) {
            doResponse(response, "Token已过期1，请重新登陆！");
            return false;
        }
         try {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey("user");
            jwtParser.parseClaimsJws(token);
            log.info(jwtParser.setSigningKey("user").parseClaimsJws(token).getBody().toString());
            return true;
        } catch (ExpiredJwtException e) {
            doResponse(response, "Token已过期2，请重新登陆！");
            return false;
        } catch (UnsupportedJwtException e) {
            doResponse(response, "Token不合法，请自重！");
            return false;
        } catch (Exception e) {
            doResponse(response, "Token不合法，请自重！");
            return false;
        }
    }
     public void doResponse(HttpServletResponse response, String info) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
         ResponseEntity<String> responseEntity = new ResponseEntity<>(info, HttpStatus.NOT_ACCEPTABLE);
        String json = JSON.toJSONString(responseEntity);
         PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
