package learning.DSWII.code.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import learning.DSWII.code.entity.TUser2;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    //private static final String secret="miKeyDavid";
    //private static final Long expiration=3600000L;


    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(TUser2 user) {
        System.out.println(expiration);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getIdUser2());
        claims.put("nameUser", user.getNameUser());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getNameUser())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // Verificar la expiracion del token
            Date now = new Date();
            if (claimJws.getBody().getExpiration().before(now)) {
                return false;
            }
            return true;
        } catch (SignatureException ex) {
            // La firma del token es invalida
            return false;
        } catch (Exception e) {
            // Otra excepcion
            return false;
        }
    }
}

/*
 * private static final String SECRET_KEY="miKeyDavid";
 * 
 * public String getToken(UserDetails user) {
 * return getToken(new HashMap<>(), user);
 * }
 * 
 * private String getToken(Map<String,Object> extraClaims, UserDetails user) {
 * return Jwts
 * .builder()
 * .setClaims(extraClaims)
 * .setSubject(user.getUsername())
 * .setIssuedAt(new Date(System.currentTimeMillis()))
 * .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
 * .signWith(getKey(), SignatureAlgorithm.HS256)
 * .compact();
 * }
 * 
 * private Key getKey() {
 * byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
 * return Keys.hmacShaKeyFor(keyBytes);
 * }
 * 
 * public String getUsernameFromToken(String token) {
 * return getClaim(token, Claims::getSubject);
 * }
 * 
 * public boolean isTokenValid(String token, UserDetails userDetails) {
 * final String username=getUsernameFromToken(token);
 * return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
 * }
 * 
 * private Claims getAllClaims(String token)
 * {
 * return Jwts
 * .parserBuilder()
 * .setSigningKey(getKey())
 * .build()
 * .parseClaimsJws(token)
 * .getBody();
 * }
 * 
 * public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
 * {
 * final Claims claims=getAllClaims(token);
 * return claimsResolver.apply(claims);
 * }
 * 
 * private Date getExpiration(String token)
 * {
 * return getClaim(token, Claims::getExpiration);
 * }
 * 
 * private boolean isTokenExpired(String token)
 * {
 * return getExpiration(token).before(new Date());
 * }
 */
