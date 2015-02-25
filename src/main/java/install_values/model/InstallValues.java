package install_values.model;

import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 22.01.2015.
 * Интерфейс описывает как должен выглядеть класс по работе с файлом cms_install_values.sql
 */
public interface InstallValues {
    /**
     * Переопределим метод toString что бы на выходе получалась нужная строка, которую можно записать в файл
     * @return Строка которая повторяет содержимое файла cms_install_values
     */
    public String toString();

    /**
     * Построим объект из строк. Распарсим строки в соответствии с реализацией класса
     * @param stringList Список строк, которые будем разбирать
     */
    public void fromString(List<String> stringList) throws RuntimeException;

    /**
     * Проверка того что объект валиден по своему наполнению
     * @return TRUE - если успешно
     */
    public boolean validate();
}
