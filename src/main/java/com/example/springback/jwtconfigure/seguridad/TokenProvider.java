package com.example.springback.jwtconfigure.seguridad;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Map;

import com.example.springback.dto.AuthTokenResponse;
import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String BEARER = "Bearer";

    @Value("${app.token.expiration.msec}")
    private Long expiracionTokenMiliSeg;

    @Value("${app.token.secret}")
    private String tokenSecret;

    public ResponseRestController<AuthTokenResponse> createToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + this.expiracionTokenMiliSeg);
        ResponseRestController<AuthTokenResponse> resp = new ResponseRestController<AuthTokenResponse>();

        try {
            if (null == userPrincipal) {

                resp.setCode(CodeResponse.ERROR);
                resp.setMessage("Password Incorrecto");

            } else {
                resp.setCode(CodeResponse.SUCCESS);
                resp.setMessage("token generado");
                resp.setBody(new AuthTokenResponse(Jwts.builder().setSubject(Integer.toString(userPrincipal.getId()))
                        .setIssuedAt(new Date()).setExpiration(expiryDate)
                        .signWith(SignatureAlgorithm.HS512, this.tokenSecret).compact(), BEARER));
            }
        } catch (Exception e) {

            resp.setCode(CodeResponse.ERROR_SERVIDOR);
            resp.setMessage("error en el microservicio " + e);
        }

        return resp;

    }

    public String createTokenAdmin(Map<String, String> authorizateAdmin) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + this.expiracionTokenMiliSeg);

        return Jwts.builder().setSubject(authorizateAdmin.get("id")).setIssuedAt(new Date()).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, this.tokenSecret).compact();
    }

    public Integer getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.tokenSecret).parseClaimsJws(token).getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.tokenSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
