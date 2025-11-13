package core.utils;

import java.util.Random;

import static core.PropertiesLoader.getPropertyByKey;

public class UserRandomStringGenerator {

    public static String generateRandomAlfanumericOfLength(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public static String getEmptyString() {
        return "";
    }

    public static String getValidUsername() {
        return getPropertyByKey("VALID_USERNAME");
    }

    public static String getValidPassword() {
        return getPropertyByKey("VALID_PASSWORD");
    }
}
