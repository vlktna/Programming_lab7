/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Выводит значения поля startDate всех элементов в порядке убывания
 */
package commands;

import collection.WorkerCollection;
import userAuthentication.User;
import java.sql.Date;
import java.util.PriorityQueue;

public class PrintFieldDescendingStartDate extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        PriorityQueue<Date> StartDate = new PriorityQueue<Date>();
        for (int i = 0; i < workerCollection.getCollection().size(); i++) {
            StartDate.add(workerCollection.getCollection().get(i).getStartDate());
        }
        return String.valueOf(StartDate);
    }
}
