/**
 * @author Veronika Volokitina
 * @version 2
 * @since 2
 */

import collection.WorkerCollection;
import threads.Receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class ServerMain {
    static int port = 8000;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ServerMain.class.getName());


        try {
//            int port = Integer.parseInt(args[0]);

            DatagramSocket socket = new DatagramSocket(port);
            logger.info("Начало main, создаем коллекцию");

            WorkerCollection workerCollection = new WorkerCollection();
            workerCollection.getCollectionFromDB();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            Receiver receiver = new Receiver(workerCollection, socket);
            executor.execute(receiver);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            if (br.ready()) {
                if (br.equals("exit")){
                    executor.shutdown();
                    socket.close();
                }
            }

        }catch(NumberFormatException e){
            logger.warning("Неверно указан порт");
        }catch(ArrayIndexOutOfBoundsException | NullPointerException e){
            logger.warning("Порт не указан");
        }catch (SocketException e) {
            logger.warning("Ошибка при создании сокета");
        }catch (IOException e){
            logger.warning("Что-то пошло не так");
        }
    }
}




