/***
 * @author Veronika Volokitina
 * @version 3
 * @since 2
 *
 * Супер-класс всех команд
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;
import java.io.IOException;
import java.io.Serializable;

public class AbstractCommand implements Serializable {

    /**
     * @param workerCollection - коллекция с работниками
     * @return ответ клиенту c результатом выполнения команды
     */
    public String execute(WorkerCollection workerCollection, User user) throws IOException {
        return null;
    }
}
