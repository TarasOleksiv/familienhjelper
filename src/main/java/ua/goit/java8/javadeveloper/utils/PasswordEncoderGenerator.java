package ua.goit.java8.javadeveloper.utils;

/**
 * Created by t.oleksiv on 01/02/2018.
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {

        int i = 0;
        while (i < 10) {
            String password = "guest";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            System.out.println(hashedPassword);
            i++;
        }

    }
}