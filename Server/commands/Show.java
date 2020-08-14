/**
 * @author Veronika Volokitina
 * @version 2
 * @since 1
 *
 * Выводит элементы коллекции
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;

public class Show extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        int size = workerCollection.getCollection().size();
        if(size != 0) {
            return String.valueOf(workerCollection.getCollection());
        }
        else{
            return  "Коллекция пуста";
        }
    }
}
