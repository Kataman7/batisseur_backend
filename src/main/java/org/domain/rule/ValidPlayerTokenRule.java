package org.domain.rule;

import org.domain.model.Board;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ValidPlayerTokenRule extends AbstractPlayerRule
{
    private static final String SECRET_KEY = System.getenv().getOrDefault("GAME_SECRET_KEY", "defaultSecretChangeMeInProd");
    private static final String HASH_ALGORITHM = System.getenv().getOrDefault("GAME_HASH_ALGORITHM", "SHA-256");
    private static final String SALT = generateSalt(); // Génère un salt aléatoire au démarrage
    private final String providedToken;

    public ValidPlayerTokenRule(String playerName, String hashedPlayerToken)
    {
        super(playerName);
        this.providedToken = hashedPlayerToken;
    }

    @Override
    public boolean isApplicable(Board board) {
        try {
            String dataToHash = getPlayerName() + SALT + SECRET_KEY;
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            String computedToken = Base64.getEncoder().encodeToString(hashBytes);

            return computedToken.equals(providedToken);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    @Override
    public int getCode() {
        return 0;
    }
}
