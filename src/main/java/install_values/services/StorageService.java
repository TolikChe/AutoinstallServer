package install_values.services;

import install_values.model.InstallValues;

/**
 * Created by Anatoly.Cherkasov on 22.01.2015.
 * Интерфес который определяет каким образом будет реализовано сохранение файла cms_install_values.
 * Для разных подсистем реализация интерфейса должна быть разной
 */
public interface StorageService {

    /**
     * Прочитаем конфиг из файла в объект
     * @param filename Имя файла из которого надо прочитать объект
     * @return Объект описывающий cms_install_values
     */
    public InstallValues readFromFile(String filename) throws Exception;
    /**
     * Запишем из объекта cms_install_values значения в файл
     * @param installValues Объект который необходимо записать в файл
     * @param filename Имя файла в который мы запишем значение объекта
     */
    public void writeToFile(InstallValues installValues, String filename) throws Exception;
    /**
     * Проверяем что бы все обязательные поля в объекте cms_install_values были заполнены
     * @param installValues Объект который необходимо записать в файл
     * @return Если true то проверка пройдена, если false то не пройдена
     */
    public boolean checkData(InstallValues installValues);
}
