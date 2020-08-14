/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Вход в систему
 */

package userAuthentication;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserHandler {

    private boolean access;
    private String name;
    private Map<String, String> users;

    public UserHandler(Map<String, String> users){
        this.users = users;
    }

    public void getUsersFromDB(){

        Map<String, String> users = new HashMap<String, String>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");

            Statement statement = connection.createStatement();
            String sql = "SELECT name, password FROM USERS";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                this.users.put(password, name);
            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
        }
    }

    private void addUserToDB(String name, String password){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (name, password) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();

            statement.close();
            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
        }
    }

    public void userCheck(String inputCommand){
        String[] line = inputCommand.trim().toLowerCase().split(" ", 2);
        switch (line[0]) {
            case "log_in":
                login();
                break;

            case "sign_up":
                signup();
                break;

            case "exit":
                System.out.println("Завершение работы");
                break;

            default:
                System.out.println("Войди(log_in) или зарегистрируйся(sign_up)");
        }
    }

    private void signup(){
        String[] data = Authentication.authentication();
        String name = data[0];
        String password = data[1];
        getUsersFromDB();
        if (this.users.containsValue(name)){
            System.out.println("Имя пользователя занято");
        }else {
            addUserToDB(name, password);
            System.out.println("Добавлен новый пользователь, напиши log_in для входа");
        }
    }

    private void login(){
        String[] data = Authentication.authentication();
        String name = data[0];
        String password = data[1];
        getUsersFromDB();
        if (this.users.containsValue(name)){
            if (this.users.containsKey(password)){
                this.access = true;
                this.name = name;
                System.out.println("Доступ получен, напиши help");
            }else{
                this.access = false;
                System.out.println("Неверный пароль");
            }
        }else{
            this.access = false;
            System.out.println("Пользователь не найден");
        }
    }

    public boolean getAccess(){
        return this.access;
    }

    public String getName(){
        return this.name;
    }
}
