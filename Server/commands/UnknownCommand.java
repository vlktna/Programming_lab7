/**
 * @author Veronika Volokitina
 * @version 2
 * @since 2
 *
 * Неизвестная команда
 */
package commands;

import collection.WorkerCollection;
import userAuthentication.User;

public class UnknownCommand extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        return "Команда не найдена";
    }
}
