/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Аутентификация пользователя
 */

package userAuthentication;

import io.TerminalRead;
import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication {

    /**
     * @return массив с именем и хешем пароля пользователя
     * @throws NoSuchAlgorithmException
     */
    public static String[] authentication(){

        String[] data = new String[2];
        Console console = System.console();
        TerminalRead input = new TerminalRead();
        System.out.println("Напиши имя пользователя:");
        String name = input.getNextInput();
        data[0] = name;
        char password[] = console.readPassword("Напиши пароль: ");
//        data[1] = String.valueOf(passwordArray);
//        System.out.println("Напиши пароль:");
//        String password = input.getNextInput();
        data[1] = getHash(String.valueOf(password));

        return data;
    }

    /**
     * @param password пароль пользователя
     * @return хеш пароля пользователя
     * @throws NoSuchAlgorithmException
     */
    private static String getHash(String password){

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e){
            return "Ошибка при хешировании пароля";
        }
    }
}
