package subsystems.services;

import config.model.SubsystemConfig;

/**
 * Created by Anatoly.Cherkasov on 26.01.2015.
 * Интерфейс который определяет методы работы с подсистемой.
 * Подсистему можно установить, скопировать дистрибутив, подготовить дистрибутив ...
 */
public interface SetupService {
    /**
     * Клонирование исходников подсистемы в указанную папку
     * @param subsystem Подсистема, чьи файлы надо склонировать
     * @param destination Папка в которую надо склонировать подсистему
     * @return Путь по которому склонирована подсистема. Если NULL то ошибка клонирования
     */
    public String cloneSubsystem(SubsystemConfig subsystem, String destination) throws Exception;


    /**
     * Деалем из Source Distrib.
     * Переносим в в папку подсистемы файл cms_install_values и вызываем distib10.bat что бы исходники превратились в готовые файлы
     * @param subsystem Подсистема, чьи исходники надо подготовить. Её исходники должны быть предваритально склонированы
     * @param destination Путь, куда была склонирована подсистема
     * @return TRUE - если все успешно
     */
    public void makeDistrib(SubsystemConfig subsystem, String destination) throws Exception;


    /**
     * Устанавливаем подсистему.
     * Запускаем механизм установки подсистемы
     * @param subsystem Подсистема, которую будем устанавливать. Её исходники должны быть предваритально склонированы и подготовлены.
     * @param destination Путь, куда была склонирована подсистема
     * @return TRUE - если все успешно
     */
    public void setupSubsystem (SubsystemConfig subsystem, String destination) throws Exception;
}
