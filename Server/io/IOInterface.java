/**
 * @author Veronika Volokitina
 * @version 2
 * @since 1
 *
 * Интерфейс для считывания команд
 */

package io;

public interface IOInterface {

    String getNextInput();
    String getCurrentInput();
    int getNextIntInput();
    Long getNextLongInput();
}
