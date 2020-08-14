/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Выполняет скрипт
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;
import java.io.IOException;
import java.util.ArrayList;

public class ExecuteScript extends AbstractCommand {

    private ArrayList<AbstractCommand> commands;

    public ExecuteScript(ArrayList<AbstractCommand> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) {
        ArrayList<String> result = new ArrayList<>();
        System.out.println(String.valueOf(commands));
        for (int i = 0; i < this.commands.size(); i++) {
            try {
                result.add(this.commands.get(i).execute(workerCollection, user));
            } catch (IOException e) {
                return "Ошибка при выполнении скрипта";
            }
        }
        return String.valueOf(result);
    }
}
