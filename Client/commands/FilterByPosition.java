/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Выводит элементы, значение поля position которых равно заданному
 */

package commands;

import aboutWorker.Position;
import aboutWorker.Worker;
import collection.WorkerCollection;
import userAuthentication.User;

import java.util.ArrayList;

public class FilterByPosition extends AbstractCommand {

    private Position positionStatus;
    public FilterByPosition(Position positionStatus){
        this.positionStatus = positionStatus;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) throws IllegalArgumentException{
        int size = workerCollection.getCollection().size();
        int counter = 0;
        ArrayList<Worker> result = new ArrayList<>();
        for (int i = 0; i < workerCollection.getCollection().size(); i++) {
            if (workerCollection.getCollection().get(i).getPosition().equals(this.positionStatus)) {
                result.add(workerCollection.getCollection().get(i));
            } else{
                counter += 1;
            }
        }
        if (counter == size){
            return "Такие элементы не найдены";
        }
        else{
            return String.valueOf(result);
        }
    }
}
