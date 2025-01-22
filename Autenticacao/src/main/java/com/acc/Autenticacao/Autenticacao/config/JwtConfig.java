package com.acc.Autenticacao.Autenticacao.config;

import com.acc.Autenticacao.Autenticacao.exceptions.KeyLoadingException;
import com.acc.Autenticacao.Autenticacao.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.InternalError.*;

@Configuration
public class JwtConfig {

    private static final String PRIVATE_KEY_FILE = "Autenticacao/private_key.pem";
    private static final String PUBLIC_KEY_FILE = "Autenticacao/public_key.pem";

    private Path getKeyPath(String fileName) {
        return Paths.get(System.getProperty("user.dir")).resolve(fileName);
    }

    @Bean
    public PrivateKey getPrivateKey() throws Exception {
        try (FileInputStream fis = new FileInputStream(PRIVATE_KEY_FILE)) {
            byte[] keyBytes = fis.readAllBytes();
            String keyContent = new String(keyBytes);

            // Limpeza do conteúdo da chave
            keyContent = keyContent.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("[\\r\\n]", "")
                    .replaceAll("\\s", "");

            if (!isBase64(keyContent)) {
                throw new KeyLoadingException(PRIVATE_KEY_ENCRYPT_ERROR);
            }

            byte[] decodedKey = Base64.getDecoder().decode(keyContent);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (IOException e) {
            throw new KeyLoadingException(FAILED_READING_PRIVATE_KEY, e);
        }
    }

    @Bean
    public PublicKey getPublicKey() throws Exception {
        try (FileInputStream fis = new FileInputStream(PUBLIC_KEY_FILE)) {
            byte[] keyBytes = fis.readAllBytes();
            String keyContent = new String(keyBytes);

            keyContent = keyContent.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("[\\r\\n]", "")
                    .replaceAll("\\s", "");

            byte[] decodedKey = Base64.getDecoder().decode(keyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (IOException e) {
            throw new KeyLoadingException(FAILED_READING_PUBLIC_KEY, e);
        }
    }

    private boolean isBase64(String string) {
        try {
            Base64.getDecoder().decode(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Bean
    public JwtService jwtService(PublicKey publicKey, PrivateKey privateKey) {
        return new JwtService(publicKey, privateKey);
    }
}
