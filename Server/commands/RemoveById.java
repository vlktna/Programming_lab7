/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Удаляет элемент коллекции по заданному ID
 */

package commands;

import collection.WorkerCollection;
import io.ExecuteStatement;
import userAuthentication.User;

public class RemoveById extends AbstractCommand {

    private int id;

    public RemoveById(int id){
        this.id = id;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) throws NumberFormatException{
        int counter = 0;
        int size = workerCollection.getCollection().size();
        String output = "";

        for (int i = 0; i < workerCollection.getCollection().size(); i++){
            if(workerCollection.getCollection().get(i).getId() == id &
                    workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())){

                ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + id);
                workerCollection.getCollection().remove(i);
                output = "Элемент удален";
            }
            else {
                counter += 1;
            }
        }
        if (counter == size){
            output = "Элемент с таким ID не найден";
        }
        return output;
    }
}
