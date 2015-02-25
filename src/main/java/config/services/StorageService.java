package config.services;

import config.model.CommonConfig;

/**
 * Created by echo on 25.02.2015.
 * Этот  интерфейс описывает действия, который должен уметь выполнять сервис занимающийсы хранением конфигруации
 * Сервис должен уметь: считывать файл конфигурации,
 *                      сохранять файл конфигурации
 *                      выделять из файла конфигурации настройки отдельных подсистем
 */
public interface StorageService {

    /**
     * Прочитать конфигурацию из файла
     * @param configFilename Имя файла с конфигурацией
     * @return Объект, содержащий файл конфигурации
     * @throws RuntimeException В случае отсутстивя файла конфигурации или проблем с его чтением возникает исключение
     */
    public CommonConfig readFromFile (String configFilename) throws RuntimeException;


}
