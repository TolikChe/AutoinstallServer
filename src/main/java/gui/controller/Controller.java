package gui.controller;

import config.controller.ConfigController;
import config.model.CommonConfig;
import config.model.SubsystemConfig;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Controller {
    @FXML private Button runButton;
    @FXML private Button saveButton;
    @FXML private TextField configNameTextField;
    @FXML private TextField tnsTextField;
    @FXML private TextField destinationTextField;
    @FXML private ChoiceBox<String> subsystemTypeChoiseBox;
    @FXML private ChoiceBox<String> setupModeChoiseBox;
    @FXML private TextField branchTextField;
    @FXML private TextField tagTextField;
    @FXML private TextField installValuesFileTextField;
    @FXML private TextField gitUrlTextField;
    @FXML private TextField distribMakeFileTextField;
    @FXML private TextField distribResultDirectoryTextField;
    @FXML private Button installValuesFileButton;
    @FXML private RadioButton branchRadioButton;
    @FXML private RadioButton tagRadioButton;
    @FXML private TreeView<String> subsystemsTreeView;

    // Группировка RadioButton
    final ToggleGroup group = new ToggleGroup();
    //
    private Stage currentStage;



    //
    public void initStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    void initialize() {

        // Режим установки (install / update)
        setupModeChoiseBox.setItems(FXCollections.observableArrayList("install", "update"));
        setupModeChoiseBox.setValue("install");

        // Тип подсистемы
        subsystemTypeChoiseBox.setItems(FXCollections.observableArrayList("SCR_CMS", "CMS_SRV_DB", "CMS_DS_ADAPTER", "CMS_DEBUG"));

        // По умолчанию ветка master
        branchTextField.setText("master");

        // По умолчанию выбрана ветка
        branchRadioButton.setToggleGroup(group);
        tagRadioButton.setToggleGroup(group);
        branchRadioButton.setSelected(true);
        tagTextField.setDisable(true);

        // Имя конфигурации по умолчанию = cfg1
        configNameTextField.setText("cfg1");

        TreeItem<String> rootItem = new TreeItem<String> ("Подсистемы");
        rootItem.setExpanded(false);
        subsystemsTreeView.setRoot(rootItem);


    }

    @FXML
    void fireBranchRadioButton(ActionEvent event){
        branchTextField.setDisable(false);
        tagTextField.setDisable(true);
    }
    @FXML
    void fireTagRadioButton(ActionEvent event){
        branchTextField.setDisable(true);
        tagTextField.setDisable(false);
    }
    @FXML
    void saveConfig(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить конфигурацию");
        File file = fileChooser.showSaveDialog(currentStage);
        /*
        if (file != null) {
            try {
                System.out.println("XXX");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        */
    }

    @FXML
    void loadConfig(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить конфигурацию");
        // Получим файл, который был выбран в диалоге
        File file = fileChooser.showOpenDialog(currentStage);
        // Если файл был выбран попробуем его прочитать
        if (file != null) {
            // Заполним общие параметры
            CommonConfig commonConfig = ConfigController.getConfig(file.getAbsolutePath());
            configNameTextField.setText(commonConfig.getConfigName());
            tnsTextField.setText(commonConfig.getTns());
            destinationTextField.setText( commonConfig.getDestinationDirectory() );

            // Заполним дерево
            TreeItem<String> rootItem = new TreeItem<String> ("Подсистемы");
            rootItem.setExpanded(true);
            for ( SubsystemConfig subsystemConfig : commonConfig.getSubsystemConfigTreeMap().values()) {
                TreeItem<String> item = new TreeItem<String> ( subsystemConfig.getOrder() + "_" + subsystemConfig.getType());
                rootItem.getChildren().add(item);
            }
            subsystemsTreeView.setRoot(rootItem);
        }
    }

}
