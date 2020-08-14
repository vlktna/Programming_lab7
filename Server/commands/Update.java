/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Обновляет значение элемента коллекции, id которого равен заданному
 */

package commands;

import aboutWorker.Worker;
import collection.WorkerCollection;
import io.ExecuteStatement;
import userAuthentication.User;

public class Update extends AbstractCommand {

    private Worker worker;
    private int id;
    public Update(Worker worker, int id){
        this.worker = worker;
        this.id = id;
    }

    /**
     * @throws NumberFormatException
     */
    @Override
    public String execute(WorkerCollection workerCollection, User user) throws NumberFormatException{
        String output = "";
        int counter = 0;
        int size = workerCollection.getCollection().size();
        for (int i = 0; i < size; i++) {
            int currentId = workerCollection.getCollection().get(i).getId();
            if (currentId == this.id &
                    workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())) {

                ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + currentId);
                this.worker.setOwner(user.getUserName());
                workerCollection.addWorkerToDB(this.worker);
                workerCollection.getCollectionFromDB();
                output = "Элемент коллекции обновлен";
            }
            else {
                counter += 1;
            }
        }
        if (counter == size){
            output = "Такой элемент не найден";
        }
        return output;
    }
}
