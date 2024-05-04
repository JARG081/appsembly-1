package appsembly.appsembly.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

public interface IJWTUtilityService {
        public String generateJWT(Long userId, String roles)
                        throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException;

        public JWTClaimsSet parseJwt(String jwt)
                        throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException,
                        JOSEException;
}
