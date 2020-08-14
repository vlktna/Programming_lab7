/**
 * @author Veronika Volokitina
 * @version 3
 * @since 2
 *
 * Команда для отправки сообщений
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;

public class MessageCommand extends AbstractCommand {

    private String message;
    public MessageCommand(String message){
        this.message = message;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        return this.message;
    }
}
