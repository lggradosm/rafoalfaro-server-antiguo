package com.rafoalfaro.server.conf.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long EXPIRED_TIME_IN_HOURS = 10 ;

    @Value("${jwt.secret}")
    private String secret;
    public String getUsernameFromToken(String token)  {
        return decodedToken(token).getSubject();
    }

    public DecodedJWT decodedToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .build().verify(token);
    }

    private Date getExpirationDateFromToken(String token){
        return decodedToken(token).getExpiresAt();
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername() )&& !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withExpiresAt(getExpiredTime())
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withSubject(userDetails.getUsername())
            .sign(algorithm);

    }

    private Date getExpiredTime(){
        //End workday 19:00 max
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,19);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.YEAR,2025);
        if (calendar.getTime().before(new Date())){
            return new Date(System.currentTimeMillis()+ 60 * 60 * 1000);
        }
        return calendar.getTime();
    }



}
