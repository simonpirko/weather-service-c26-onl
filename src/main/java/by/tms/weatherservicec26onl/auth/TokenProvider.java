package by.tms.weatherservicec26onl.auth;

import by.tms.weatherservicec26onl.entity.User;
import by.tms.weatherservicec26onl.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public Optional<Authentication> getAuthentication(String token, HttpServletRequest request) {

        SecretKey key = Jwts.SIG.HS256.key().build();

        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        Long id = Long.valueOf(payload.getId());
        String username = payload.getSubject();

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(id)
                .username(username)
                .build();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userPrincipal, payload, Set.of(new SimpleGrantedAuthority(String.format("USERNAME_%s", username))));
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return Optional.of(usernamePasswordAuthenticationToken);
    }

    public String generateToken(User user) {
        SecretKey key = Jwts.SIG.HS256.key().build();

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Map<String, String> claims = new HashMap<>();


        JwtBuilder builder = Jwts.builder()
                .id(user.getId().toString())
                .issuedAt(now)
                .issuer(user.getUsername())
                .subject(user.getUsername())
                .claims(claims)
                .signWith(key);

        if (jwtProperties.getTtlMillis() > 0) {
            long expMillis = nowMillis + jwtProperties.getTtlMillis();
            Date exp = new Date(expMillis);
            builder.expiration(exp);
        }
        return builder.compact();
    }
}

