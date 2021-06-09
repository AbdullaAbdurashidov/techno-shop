package com.codeforce.shop.jwt;

import com.codeforce.shop.dto.UserDTO;
import com.codeforce.shop.service.impl.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.token.secret}")
    private String JWT_TOKEN_SECRET;

    @Autowired
    private UserService userService;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_SECRET).compact();
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(JWT_TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public UserDTO getUserFromToken(HttpServletRequest request){
        String requestHeader = request.getHeader("Authorization");
        String token;
        String username = null;
        if(requestHeader != null && requestHeader.startsWith("Bearer ")){
            token = requestHeader.substring(7);
            try{
                if(!isTokenExpired(token))
                    username = getUsernameFromToken(token);
                else
                    username = null;
            }catch (IllegalArgumentException e){
                System.out.println("Could not retrieve username");
            }catch (ExpiredJwtException e){
                System.out.println("Token is expired");
            }
        }else{
            System.out.println("Token is not accepted");
        }
        if(username!=null)
            return userService.findByUsername(username);
        else
            return null;
    }

}
