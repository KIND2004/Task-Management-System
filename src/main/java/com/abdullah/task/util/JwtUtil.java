package com.abdullah.task.util;

import com.abdullah.task.exception.AccessDeniedException;
import com.abdullah.task.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "JBX/b]MJ)x0M.}Ve6i(b+[pt,f(T53A8u+Z8Mwx/;UQc@V@&RFm%ubutt)4;x]7X";
    private static final long EXPIRY_DURATION = 60 * 30;

    public String generateAccessToken(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        long expiryTime = currentTimeMillis + EXPIRY_DURATION * 1000;

        Date issuedAt = new Date(currentTimeMillis);
        Date expiryAt = new Date(expiryTime);

        Claims claims = Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        claims.put("fullName", user.getFullName());
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Claims verifyAccessToken(String authorization) throws Exception {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authorization).getBody();
        } catch (Exception e) {
            throw new AccessDeniedException("Un Authorized");
        }
    }
}
