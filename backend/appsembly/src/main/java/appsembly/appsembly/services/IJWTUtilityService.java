package appsembly.appsembly.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
// Definición de la interfaz IJWTUtilityService, que declara los métodos para generar y analizar JWTs
public interface IJWTUtilityService {
        // Método para generar un JWT. Recibe el ID de usuario y los roles como parámetros, y devuelve un String con el JWT generado.
        // Puede lanzar varias excepciones relacionadas con la criptografía y la E/S.
        public String generateJWT(Long userId, String roles)
                        throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException;
        // Método para analizar un JWT. Recibe el JWT como parámetro y devuelve un JWTClaimsSet con los datos del token.
        // Puede lanzar varias excepciones relacionadas con la criptografía, la E/S y el análisis del token.
        public JWTClaimsSet parseJwt(String jwt)
                        throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException,
                        JOSEException;
}
