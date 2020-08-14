/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Класс работника
 */

package aboutWorker;

import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;

public class Worker implements Serializable {

    /** ID работника */
    private int id; //Поле не может быть null, Строка не может быть пустой

    /** Имя работника */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /** Координаты работника */
    private Coordinates coordinates; //Поле не может быть null

    /** Дата создания работника */
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    /** Зарплата работника */
    private Long salary; //Поле не может быть null, Значение поля должно быть больше 0

    /** Дата начала работы */
    private Date startDate; //Поле не может быть null

    /** Дата окончания работы */
    private Date endDate; //Поле может быть null

    /** Должность работника */
    private Position position; //Поле не может быть null

    /** Работодатель */
    private Organization organization; //Поле не может быть null

    /** Владелец */
    private String owner; //Поле не может быть null

    /**
     * Конструткор - создание нового объекта
     * @param name - имя
     * @param coordinates - координаты
     * @param salary - зарплата
     * @param position - должность
     * @param organization - работодатель
     */
    public Worker(int id, String name, Coordinates coordinates, Date creationDate, Long salary,
                  Date startDate, Date endDate, Position position, Organization organization, String owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.organization = organization;
        this.owner = owner;
    }

    public Worker(String name, Coordinates coordinates, Long salary,
                  Date startDate, Date endDate, Position position, Organization organization, String owner) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = Date.valueOf(LocalDate.now());
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.organization = organization;
        this.owner = owner;
    }

    /**
     * @return информацию о работнике
     */
    @Override
    public String toString() {
        return "Worker: " +
                "\nid = " + id +
                "\nname = " + this.name +
                "\ncoordinates = " + this.coordinates +
                "\ncreationDate = " + this.creationDate +
                "\nsalary = " + this.salary +
                "\nstartDate = " + this.startDate +
                "\nendDate = " + this.endDate +
                "\nposition = " + this.position +
                "\norganisation = " + this.organization +
                "\nowner = " + this.owner + "\n";
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Long getSalary() {
        return this.salary;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Position getPosition() {
        return this.position;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public String getOwner(){
        return this.owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

}




