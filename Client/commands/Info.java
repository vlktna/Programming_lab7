/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Выводит справку по коллекции
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;

public class Info extends AbstractCommand{

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        return workerCollection.toString();
    }
}
