package controllers;

import com.jfoenix.controls.*;
import entities.User;
import entities.Usermaster;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.DBHelper;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class search_controller extends communs implements Initializable{

    @FXML TableView<Usermaster> searchTable;
    @FXML TableColumn<Usermaster,String> idColumn;
    @FXML TableColumn<Usermaster,String> fnameColumn;
    @FXML TableColumn<Usermaster,String> lnameColumn;
    @FXML TableColumn<Usermaster,String> bdateColumn;
    @FXML TableColumn<Usermaster,String> bplaceColumn;
    @FXML TableColumn<Usermaster,String> sexColumn;
    @FXML TableColumn<Usermaster,String> addressColumn;
    @FXML JFXComboBox<String> jfxComboBox = new JFXComboBox();
    @FXML JFXTextField lastName = new JFXTextField();
    @FXML JFXTextField commun = new JFXTextField();
    @FXML HBox hBox = new HBox();
    @FXML JFXButton load = new JFXButton();
    @FXML JFXButton capture = new JFXButton();
    @FXML JFXButton searchButton = new JFXButton();
    private String choose = "ID";
    public ObservableList<Usermaster> data;
    public DBHelper dbHelper;
    private TableRow<Usermaster> selectedRow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildCellFactory();
        buildData();
        searchTable.setRowFactory( tv -> {
            TableRow<Usermaster> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    // set onTableViewItemSelected (DoubleClick)
                    Usermaster rowData = row.getItem();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/popups/result.fxml"));
                        Scene scene = new Scene(root);

                        Stage stage = new Stage();
                        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setTitle("ABC");
                        stage.setScene(scene);
                        stage.show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                // save the selected row to use it in the delete and update method
                selectedRow=row;
            });
            return row ;
        });

        jfxComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String before, String after) {
                capture.setStyle("-fx-background-color: #4c86af;-fx-font-size:14px;-fx-text-fill: white");
                capture.setGraphic(new ImageView(new Image("/ui/images/capture_white.png",15,15,true,true)));
                load.setStyle("-fx-background-color: #4c86af;-fx-font-size:14px;-fx-text-fill: white");
                load.setGraphic(new ImageView(new Image("/ui/images/load_white.png",15,15,true,true)));
                switch(after) {
                    case "ID" :
                        System.out.println("ID");
                        hBox.getChildren().remove(lastName);
                        hBox.getChildren().remove(capture);
                        hBox.getChildren().remove(load);
                        if(!hBox.getChildren().contains(commun)){
                            hBox.getChildren().add(2,commun);
                        }
                        commun.setPromptText("ID");
                        choose = "ID";
                        break;
                    case "First Name" :
                        System.out.println("First Name");
                        hBox.getChildren().remove(lastName);
                        hBox.getChildren().remove(capture);
                        hBox.getChildren().remove(load);
                        if(!hBox.getChildren().contains(commun)){
                            hBox.getChildren().add(2,commun);
                        }
                        commun.setPromptText("First Name");
                        choose = "First Name";
                        break;
                    case "Last Name" :
                        System.out.println("Last Name");
                        hBox.getChildren().remove(lastName);
                        hBox.getChildren().remove(capture);
                        hBox.getChildren().remove(load);
                        if(!hBox.getChildren().contains(commun)){
                            hBox.getChildren().add(2,commun);
                        }
                        commun.setPromptText("Last Name");
                        choose = "Last Name";
                        break;
                    case "Full Name" :
                        System.out.println("Full Name");
                        hBox.getChildren().remove(capture);
                        hBox.getChildren().remove(load);
                        if(!hBox.getChildren().contains(commun)){
                            hBox.getChildren().add(2,commun);
                        }
                        if (!hBox.getChildren().contains(lastName)){
                            hBox.getChildren().add(3,lastName);
                        }
                        commun.setPromptText("First Name");
                        lastName.setPromptText("Last Name");
                        choose = "Full Name";
                        break;
                    case "Wilaya" :
                        System.out.println("Wilaya");
                        hBox.getChildren().remove(lastName);
                        hBox.getChildren().remove(capture);
                        hBox.getChildren().remove(load);
                        if(!hBox.getChildren().contains(commun)){
                            hBox.getChildren().add(2,commun);
                        }
                        commun.setPromptText("Wilaya");
                        choose = "Wilaya";

                        break;
                    case "Photo" :
                        System.out.println("Photo");
                        hBox.getChildren().remove(lastName);
                        hBox.getChildren().remove(commun);
                        load.setText("Load");
                        capture.setText("Capture");
                        hBox.getChildren().remove(searchButton);
                        hBox.getChildren().add(2,load);
                        hBox.getChildren().add(3,capture);
                        hBox.getChildren().add(4,searchButton);
                        choose = "Photo";

                        break;
                }
            }
        });
    }

    private void buildData() {
        data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        TreeSet<User> users = dbHelper.getUserByLastName("a");
        for (User u : users) {
            Usermaster cm = new Usermaster();
            cm.setId(u.getId());
            cm.setFirstName(u.getFirstName());
            cm.setLastName(u.getLastName());
            cm.setBirthDate(u.getBirthDate().toString());
            cm.setBirthPlace(u.getBirthPlace());
            cm.setSex(u.getSex()+"");
            cm.setAddress(u.getAddress());
            data.add(cm);
        }
        searchTable.setItems(data);
    }
    private void buildCellFactory() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("id"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("firstName"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("lastName"));
        bdateColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("birthDate"));
        bplaceColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("birthPlace"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("sex"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("address"));
    }

    public void performSearch(ActionEvent actionEvent) {
            if (validateTextField(commun)) {
                switch (choose) {
                    case "ID":
                        searchTable.setItems(searchById(commun.getText().toString()));
                        break;
                    case "First Name":
                        searchTable.setItems(searchByFirstName(commun.getText().toString()));
                        break;
                    case "Last Name":
                        searchTable.setItems(searchByLastName(commun.getText().toString()));
                        break;
                    case "Full Name":
                        searchTable.setItems(searchByFullName(lastName.getText().toString(), commun.getText().toString()));
                        break;
                    case "Wilaya":
                        searchTable.setItems(searchByBirthPlace(commun.getText().toString()));
                        break;
                    case "Photo":
                        // Put here searchByPhoto
                        System.out.println("Search by photo");
                        break;
                }
            }
    }

    private boolean validateTextField(JFXTextField textField) {
        if(textField.getText().toString() == null || textField.getText().toString().isEmpty()){
            return false;
        }return true;
    }

    private ObservableList<Usermaster> searchById(String id){
        ObservableList<Usermaster> data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        User user = dbHelper.getUserById(id);
            Usermaster cm = new Usermaster();
            cm.setId(user.getId());
            cm.setFirstName(user.getFirstName());
            cm.setLastName(user.getLastName());
            cm.setBirthDate(user.getBirthDate().toString());
            cm.setBirthPlace(user.getBirthPlace());
            cm.setSex(user.getSex()+"");
            cm.setAddress(user.getAddress());
            data.add(cm);
        return data;
    }
    private ObservableList<Usermaster> searchByFirstName(String fName){
        ObservableList<Usermaster> data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        TreeSet<User> users = dbHelper.getUserByFirstName(fName);
        for (User u : users) {
            Usermaster cm = new Usermaster();
            cm.setId(u.getId());
            cm.setFirstName(u.getFirstName());
            cm.setLastName(u.getLastName());
            cm.setBirthDate(u.getBirthDate().toString());
            cm.setBirthPlace(u.getBirthPlace());
            cm.setSex(u.getSex()+"");
            cm.setAddress(u.getAddress());
            data.add(cm);
        }
        return data;
    }
    private ObservableList<Usermaster> searchByLastName(String lName){
        ObservableList<Usermaster> data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        TreeSet<User> users = dbHelper.getUserByLastName(lName);
        for (User u : users) {
            Usermaster cm = new Usermaster();
            cm.setId(u.getId());
            cm.setFirstName(u.getFirstName());
            cm.setLastName(u.getLastName());
            cm.setBirthDate(u.getBirthDate().toString());
            cm.setBirthPlace(u.getBirthPlace());
            cm.setSex(u.getSex()+"");
            cm.setAddress(u.getAddress());
            data.add(cm);
        }
        return data;
    }
    private ObservableList<Usermaster> searchByFullName(String lName, String fName){
        ObservableList<Usermaster> data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        TreeSet<User> users = dbHelper.getUserByFullName(lName,fName);
        for (User u : users) {
            Usermaster cm = new Usermaster();
            cm.setId(u.getId());
            cm.setFirstName(u.getFirstName());
            cm.setLastName(u.getLastName());
            cm.setBirthDate(u.getBirthDate().toString());
            cm.setBirthPlace(u.getBirthPlace());
            cm.setSex(u.getSex()+"");
            cm.setAddress(u.getAddress());
            data.add(cm);
        }
        return data;
    }
    private ObservableList<Usermaster> searchByBirthPlace(String bPlace){
        ObservableList<Usermaster> data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        TreeSet<User> users = dbHelper.getUserByBirthPlace(bPlace);
        for (User u : users) {
            Usermaster cm = new Usermaster();
            cm.setId(u.getId());
            cm.setFirstName(u.getFirstName());
            cm.setLastName(u.getLastName());
            cm.setBirthDate(u.getBirthDate().toString());
            cm.setBirthPlace(u.getBirthPlace());
            cm.setSex(u.getSex()+"");
            cm.setAddress(u.getAddress());
            data.add(cm);
        }
        return data;
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        System.out.println(selectedRow);
    }
    public void onUpdateClick(ActionEvent actionEvent) {
        if (selectedRow!=null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/popups/update.fxml"));
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.initStyle(StageStyle.DECORATED);
                scene.getStylesheets().add(getClass().getResource("/ui/style/style_add.css").toExternalForm());

                stage.setTitle("ABC");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
