/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Сортирует коллекцию в порядке возратсания ID
 */

package commands;

import aboutWorker.Worker;
import collection.WorkerCollection;
import io.ExecuteStatement;
import userAuthentication.User;
import java.util.Comparator;

public class PrintAscending extends AbstractCommand{

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        Comparator<Worker> comparator = Comparator.comparing(Worker::getId);
        workerCollection.getCollection().sort(comparator);
        ExecuteStatement.execute("DELETE FROM worker_collection");

        for(int i = 0; i < workerCollection.getCollection().size(); i++){
            workerCollection.addWorkerToDB(workerCollection.getCollection().get(i));
        }
        return "Коллекция отсортирована по возрастанию ID";
    }
}
