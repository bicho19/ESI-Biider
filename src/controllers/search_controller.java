package controllers;

import entities.User;
import entities.Usermaster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ObservableList<Usermaster> data;
    private DBHelper dbHelper;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildCellFactory();
        buildData();

    }


    private void buildData() {
        data = FXCollections.observableArrayList();
        dbHelper = new DBHelper();
        TreeSet<User> users = dbHelper.getUserByLastName("MOHAMED");
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
}
