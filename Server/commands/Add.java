/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Добавляет новый элемент в коллекцию
 */

package commands;

import aboutWorker.Worker;
import collection.WorkerCollection;
import userAuthentication.User;

public class Add extends AbstractCommand{

    private Worker worker;
    public Add(Worker worker){
        this.worker = worker;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) {
        this.worker.setOwner(user.getUserName());
        workerCollection.addWorkerToDB(this.worker);
        workerCollection.getCollectionFromDB();
        return "Новый элемент добавлен в коллекцию";
    }
}
