/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Удаляет из коллекции все элементы, ID которых меньшие, чем заданный
 */

package commands;

import collection.WorkerCollection;
import io.ExecuteStatement;
import userAuthentication.User;

public class RemoveLower extends AbstractCommand {

    private int id;
    public RemoveLower(int id){
        this.id = id;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) throws NumberFormatException{
        int size = workerCollection.getCollection().size();
        int counter = 0;
        String output = "";
        for (int i = 0; i < workerCollection.getCollection().size(); i++) {
            int currentId = workerCollection.getCollection().get(i).getId();
            if (currentId < this.id &
                    workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())) {

                ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + currentId);
                workerCollection.getCollection().remove(i);
                output = "Элемент удален";
            }
            else{
                counter += 1;
            }
        }
        if (counter == size){
            output = "Такие элементы не найдены";
        }
        return output;
    }
}
