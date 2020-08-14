/***
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Класс, отвечающий за координаты работника
 */

package aboutWorker;

import java.io.Serializable;

public class Coordinates implements Serializable {

    /** Координата х */
    private Integer x;

    /** Координата y */
    private Integer y; //Поле не может быть null

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[x = " + this.getX() + ", y = " + this.getY() + "]";
    }
}
