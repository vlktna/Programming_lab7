/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Выполнение SQL команды
 */

package io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class ExecuteStatement {
    static Logger logger = Logger.getLogger(ExecuteStatement.class.getName());

    public static void execute(String sqlCommand){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");
            Statement st = connection.createStatement();
            st.execute(sqlCommand);
            st.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.warning("Ошибка при подключении к базе данных");
        }
    }
}
