/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Очищает коллекцию
 */

package commands;

import collection.WorkerCollection;
import io.ExecuteStatement;
import userAuthentication.User;

public class Clear extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        int size = workerCollection.getCollection().size();
        int counter = 0;

        for (int i = 0; i < size; i++){
            if (workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())){
                int id = workerCollection.getCollection().get(i).getId();
                ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + id);
                workerCollection.getCollection().remove(i);
            }else{
                counter += 1;
            }
        }

        if (counter == size){
            return "Элементы не найдены";
        }else{
            return "Элементы удалены";
        }

    }
}
