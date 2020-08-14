/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Преобразует входящую строку в команду и аргумент
 */

package collection;

import aboutWorker.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Logger;

public class WorkerCollection {
    Logger logger = Logger.getLogger(WorkerCollection.class.getName());

    /** Коллекция с работниками */
    private LinkedList<Worker> workers;

    /** Дата создания коллекции */
    private LocalDate date;

    public void getCollectionFromDB() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM worker_collection";
            ResultSet resultSet = statement.executeQuery(sql);

            LinkedList<Worker> workers = new LinkedList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer coordinates_x = resultSet.getInt("coordinates_x");
                Integer coordinates_y = resultSet.getInt("coordinates_y");
                Long salary = resultSet.getLong("salary");
                Date creation_date = resultSet.getDate("creation_date");
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");
                Position position = Position.valueOf(resultSet.getString("position"));
                Integer employees_count = resultSet.getInt("employees_count");
                OrganizationType type = OrganizationType.valueOf(resultSet.getString("organization_type"));
                String owner = resultSet.getString("owner");

                workers.add(new Worker(id, name, new Coordinates(coordinates_x, coordinates_y), creation_date, salary,
                        start_date, end_date, position, new Organization(employees_count, type), owner));
            }

            statement.close();
            connection.close();

            this.workers = workers;
            this.date = LocalDate.now();
        }catch (SQLException | ClassNotFoundException e){
            logger.warning("Ошибка при загрузке данных из базы");
        }
    }

    public void addWorkerToDB(Worker worker){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO worker_collection (name, coordinates_x, " +
                    "coordinates_y, salary, creation_date, start_date, end_date, position, employees_count, organization_type, " +
                    "owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, worker.getName());
            statement.setInt(2, worker.getCoordinates().getX());
            statement.setInt(3, worker.getCoordinates().getY());
            statement.setLong(4, worker.getSalary());
            statement.setDate(5, worker.getCreationDate());
            statement.setDate(6, worker.getStartDate());
            statement.setDate(7, worker.getEndDate());
            statement.setString(8, String.valueOf(worker.getPosition()));
            statement.setInt(9, worker.getOrganization().getEmployeesCount());
            statement.setString(10, worker.getOrganization().getType());
            statement.setString(11, worker.getOwner());
            statement.execute();

            statement.close();
            connection.close();
        }catch (SQLException | ClassNotFoundException e){
            logger.warning("Ошибка при загрузке данных в базу");
        }
    }

    public LinkedList<Worker> getCollection() {
        return this.workers;
    }

    @Override
    public String toString() {
        return "Тип коллекции: " + workers.getClass() +
                "\nДата инициализации: " + this.date +
                "\nКоличество элементов: " + workers.size();
    }
}
