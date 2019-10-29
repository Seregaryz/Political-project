package support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServiceHelper {

    public static String md5Custom(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static boolean isSavedInCookies(HttpServletRequest request) {
        String login, password;
        boolean hasEmailCookies = false;
        boolean hasPassCookies = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                    hasEmailCookies = true;
                    request.getSession().setAttribute("login", login);
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                    hasPassCookies = true;
                    request.getSession().setAttribute("password", password);
                }
            }
        }
        return hasEmailCookies && hasPassCookies;
    }
}
