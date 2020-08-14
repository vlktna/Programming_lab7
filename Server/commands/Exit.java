/**
 * @author Veronika Volokitina
 * @version 3
 * @since 2
 *
 * Завершение клиентского приложения
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;

public class Exit extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        return "Завершение работы, сохранение коллекции";
    }
}
