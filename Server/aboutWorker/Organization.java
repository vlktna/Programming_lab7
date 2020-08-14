/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Класс работадателя
 */

package aboutWorker;

import java.io.Serializable;

public class Organization implements Serializable {

    /** Поле количество работников в компании */
    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0

    /** Тип организации */
    private OrganizationType type; //Поле не может быть null


    public Organization(Integer employeesCount, OrganizationType type){
        this.employeesCount = employeesCount;
        this.type = type;
    }

    public Integer getEmployeesCount(){
        return this.employeesCount;
    }

    public String getType(){
        return String.valueOf(this.type);
    }

    @Override
    public String toString() {
        return "[количество работников = " + this.getEmployeesCount() + ", тип = " + this.getType() + "]";
    }
}
