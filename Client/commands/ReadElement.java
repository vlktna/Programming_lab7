/**
 * @author Veronika Volokitina
 * @version 2
 * @since 1
 *
 * Считывает элемент коллекции
 */

package commands;

import aboutWorker.*;
import io.TerminalRead;
import userAuthentication.User;
import userAuthentication.UserHandler;

import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class ReadElement {

    /**
     * @return worker - новый элемент коллекции
     * @throws InputMismatchException
     * @throws NumberFormatException
     */
    public static Worker readElement() throws InputMismatchException, NumberFormatException{
        Worker worker;
        TerminalRead input = new TerminalRead();


        /** Имя работника*/
        String name;
        do {
            System.out.println("Введите имя:");
            name = input.getNextInput().trim();
            if (name.equals("")){
                System.out.println("У работника должно быть имя");
            }
        } while (name.equals(""));


        /** Координата x */
        Integer x = null;

        do {
            System.out.println("Введите координаты, x:");
            try {
                x = input.getNextIntInput();
            } catch (InputMismatchException e) {
                System.out.println("Введи целое число от -2147483648 до 2147483647");
            }
        }while (x == null);

        /** Координата y */
        Integer y = null;
        String y1;

        do {
            System.out.println("y:");
            try {
                y1 = input.getNextInput();
                y = Integer.parseInt(y1);
            } catch (NumberFormatException e){
                System.out.println("Введи целое число от -2147483648 до 2147483647");
            } catch (NullPointerException e){
                System.out.println("Значение у не может быть null");
            }

        } while (y == null);


        /** Зарплата работника x */
        Long salary = 0L;
        do {
            System.out.println("Введите размер зарплаты (целое положительное число):");
            try{
                salary = input.getNextLongInput();
            }
            catch (InputMismatchException e){
                System.out.println("Введите целое число от 1 до 9223372036854775807");
            }

        } while (salary <= 0L);


        /** Дата приема на работу */
        Date startDate = null;
        String date1;

        do {
            try {
                System.out.println("Введите дату приема на работу в формате YYYY-MM-DD");
                date1 = input.getNextInput();
                startDate = Date.valueOf(date1);
            }catch (DateTimeParseException | IllegalArgumentException e){
                System.out.println("Неверный формат записи");
            }
        } while (startDate == null);

        /** Дата увольнения */
        Date endDate = null;
        String date2;

        try {
            System.out.println("Введите дату увольнения формате YYYY-MM-DD");
            date2 = input.getNextInput();
            if (date2.equals("null")) {
                endDate = null;
            }else {
                endDate = Date.valueOf(date2);
            }
        }catch (DateTimeParseException | IllegalArgumentException e){
            System.out.println("Неверный формат записи");

        }


        /** Должность работника */
        String position1;
        Position position = null;

        do {
            System.out.println("Выберите должность: LABORER (1), ENGINEER (2), HEAD_OF_DEPARTMENT (3), DEVELOPER (4), BAKER (5)");
            position1 = input.getNextInput().trim().toLowerCase();
            switch (position1) {
                case "laborer":
                case "1":
                    position = Position.LABORER;
                    break;

                case "engineer":
                case "2":
                    position = Position.ENGINEER;
                    break;

                case "head_of_department":
                case "head of department":
                case "3":
                    position = Position.HEAD_OF_DEPARTMENT;
                    break;

                case "developer":
                case "4":
                    position = Position.DEVELOPER;
                    break;

                case "baker":
                case "5":
                    position = Position.BAKER;
                    break;

                default:
                    System.out.println("Такой должности нет");
                    break;
            }
        } while(position == null);


        /** Тип компании */
        String organizationType1;
        OrganizationType organizationType = null;

        do {
            System.out.println("Выберите тип организации: COMMERCIAL (1), PRIVATE_LIMITED_COMPANY (2), OPEN_JOINT_STOCK_COMPANY (3)");
            organizationType1 = input.getNextInput().trim().toLowerCase();
            switch (organizationType1) {
                case "commercial":
                case "1":
                    organizationType = OrganizationType.COMMERCIAL;
                    break;

                case "private_limited_company":
                case "private limited company":
                case "2":
                    organizationType = OrganizationType.PRIVATE_LIMITED_COMPANY;
                    break;

                case "open_joint_stock_company":
                case "open joint stock company":
                case "3":
                    organizationType = OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                    break;

                default:
                    System.out.println("Такого варианта нет");
                    break;
            }
        } while(organizationType == null);


        /** Количество работников в компании */
        Integer employeesCount = 0;

        do {
            System.out.println("Введите количество работников в компании");
            try{
                employeesCount = input.getNextIntInput();
            }
            catch (InputMismatchException e){
                System.out.println("Введи целое число от 1 до 2147483647");
            }
        } while (employeesCount <= 0);

        worker = new Worker(name, new Coordinates(x, y), salary, startDate, endDate, position, new Organization(employeesCount, organizationType), "");
        return worker;
    }
}
