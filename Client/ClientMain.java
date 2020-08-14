/**
 * @author Veronika Volokitina
 * @version 3
 * @since 2
 *
 */

import commands.AbstractCommand;
import commands.Exit;
import io.Communication;
import io.CreateObject;
import io.TerminalRead;
import userAuthentication.User;
import userAuthentication.UserHandler;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ClientMain {
    static int port = 8000;
    static String host = "localhost";

    public static void main(String[] args){

        try {
//            int port = Integer.parseInt(args[0]);
//            String host = args[1];

            DatagramSocket socket = new DatagramSocket();
            TerminalRead terminalRead = new TerminalRead();
            AbstractCommand command = new AbstractCommand();
            Communication communication = new Communication(host, port, socket);

            System.out.println("Войди(log_in) или зарегистрируйся(sign_up)");
            Map<String, String> users = new HashMap<String, String>();
            UserHandler userHandler = new UserHandler(users);

            while (!userHandler.getAccess()) {
                userHandler.userCheck(terminalRead.getNextInput());
                if (terminalRead.getCurrentInput().equals("exit")) {
                    socket.close();
                    break;
                }
            }

            User user = new User(userHandler.getName());
            CreateObject createObject = new CreateObject();

            while (userHandler.getAccess()) {
                socket.setSoTimeout(1000);

                command = createObject.createObject(terminalRead.getNextInput());
                communication.sendUser(user);

                communication.sendCommand(command);
                communication.receiveMessage();

                if (command instanceof Exit) {
                    socket.close();
                    break;
                }
            }

        } catch (SocketTimeoutException e) {
            System.out.println("Сервер умер");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Напиши <port> <host>");
        } catch (UnknownHostException e) {
            System.out.println("Хост не найден");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Неверно указан порт");
        }
    }
}
