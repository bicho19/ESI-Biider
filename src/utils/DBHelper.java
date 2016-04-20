package utils;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import entities.Admin;
import entities.User;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class DBHelper {
    private java.sql.Connection connect = null;
    private java.sql.Statement statement = null;
    private java.sql.PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public DBHelper(){

    }

    public boolean isTableExist(String tableName){
        connect = CoonectionConfiguration.getConnection();
        DatabaseMetaData dbm = null;
        boolean exist = false;
        try {
            dbm = (DatabaseMetaData) connect.getMetaData();
            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                // Table does exist
                exist = true;
            }
            else {
                // Table does not exist
                exist = false;
            }
        } catch (SQLException e) {
            System.err.println("Got an exception searching for table!");
            e.printStackTrace();
        } finally {
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return exist;
    }

    public void createTable(String sql){
        connect = CoonectionConfiguration.getConnection();
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Got an exception creating table!");
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Admin checkLoginAdmin(Admin admin){
        Admin admin1 = new Admin();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM admins WHERE username = ? AND password = ?");
            preparedStatement.setString(1,admin.getUsername());
            preparedStatement.setString(2,admin.getPassword());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    admin1.setUsername(resultSet.getString("username"));
                }
            } else {
                admin1 = null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return admin1;

    }

    public void addAdmin(String username, String password){
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("insert into  admins (username, password) values (?, ?)");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Got an exception adding admins!");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void addUser (User user){
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect
                    .prepareStatement("insert into  users (id, last_name, first_name, " +
                            "birth_date, birth_place, address, finger_print) " +
                            "values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1,user.getId());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getBirthDate());
            preparedStatement.setString(5,user.getBirthPlace());
            preparedStatement.setString(6,user.getAddress());
            preparedStatement.setString(7,user.getFingerPrint());
            preparedStatement.execute();
            System.out.println("Person with the Id: "+user.getId()+"has been added to users");
        } catch (SQLException e) {
            System.err.println("Got an exception adding user!");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User setUserFromResult(ResultSet result) throws SQLException {
        User user = new User();user.setId(result.getString("id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setBirthDate(result.getString("birth_date"));
        user.setBirthPlace(result.getString("birth_place"));
        user.setAddress(result.getString("address"));
        user.setFingerPrint(result.getString("finger_print"));
        return user;
    }

    public User getUserById(String id){
        User user = new User();
        ArrayList<String> photos = new ArrayList<String>();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT *,user_photo FROM users,userphoto WHERE users.id =userphoto.user_id AND id = ?");
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = setUserFromResult(resultSet);
                photos.add(resultSet.getString("user_photo"));
            }
            user.setPhotos(photos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public TreeSet<User> getUserByFullName(String lName, String fName){
        TreeSet<User> result = new TreeSet<>();
        int i=0;
        int j=0;
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT *,user_photo FROM users,userphoto WHERE last_name = ? AND first_name = ?" +
                                                        "AND users.id = userphoto.user_id");
            preparedStatement.setString(1,lName);
            preparedStatement.setString(2,fName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
                for(User u :result){
                    if(u.getId().equals(resultSet.getString("user_id"))){
                        u.getPhotos().add(resultSet.getString("user_photo"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public TreeSet<User> getUserByFirstName(String fName){
        TreeSet<User> result = new TreeSet<>();
        int i=0;
        int j=0;
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT *,user_photo FROM users,userphoto WHERE first_name = ?" +
                    "AND users.id = userphoto.user_id");
            preparedStatement.setString(1,fName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
                for(User u :result){
                    if(u.getId().equals(resultSet.getString("user_id"))){
                        u.getPhotos().add(resultSet.getString("user_photo"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public TreeSet<User> getUserByLastName(String lName){
        TreeSet<User> result = new TreeSet<>();
        int i=0;
        int j=0;
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT *,user_photo FROM users,userphoto WHERE last_name = ?" +
                    "AND users.id = userphoto.user_id");
            preparedStatement.setString(1,lName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
                for(User u :result){
                    if(u.getId().equals(resultSet.getString("user_id"))){
                        u.getPhotos().add(resultSet.getString("user_photo"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public TreeSet<User> getUserByBirthPlace (String birthPlace){
        TreeSet<User> result = new TreeSet<>();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT *,user_photo FROM users,userphoto WHERE birth_place = ?" +
                                                            "AND users.id = userphoto.user_id");
            preparedStatement.setString(1,birthPlace);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
                for(User u :result){
                    if(u.getId().equals(resultSet.getString("user_id"))){
                        u.getPhotos().add(resultSet.getString("user_photo"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public TreeSet<User> getUserByNameAndBirthPlace (String lName, String fName, String bP){
        TreeSet<User> result = new TreeSet<>();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT *,user_photo FROM users,userphoto WHERE birth_place = ?" +
                    "AND last_name = ? AND first_name = ? AND users.id = userphoto.user_id");
            preparedStatement.setString(1,bP);
            preparedStatement.setString(2,lName);
            preparedStatement.setString(3,fName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
                for(User u :result){
                    if(u.getId().equals(resultSet.getString("user_id"))){
                        u.getPhotos().add(resultSet.getString("user_photo"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}

