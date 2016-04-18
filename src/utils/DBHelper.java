package utils;


import entities.Admin;
import entities.User;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public DBHelper(){

    }

    public boolean isTableExist(String tableName){
        connect = CoonectionConfiguration.getConnection();
        DatabaseMetaData dbm = null;
        boolean exist = false;
        try {
            dbm = connect.getMetaData();
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

    public void addAdmin(String username, String password){
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect
                    .prepareStatement("insert into  admins (username, password) values (?, ?)");
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

    public User setUserFromResult(ResultSet result) throws SQLException {
        User user = new User();user.setId(result.getString("id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setBirthDate(result.getString("birth_date"));
        user.setBirthPlace(result.getString("birth_place"));
        user.setAddress(result.getString("address"));
        user.setFingerPrint(result.getBlob("finger_print"));
        return user;
    }

    public User getUserById(String id){
        User user = new User();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = setUserFromResult(resultSet);
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
        return user;
    }

    public List<User> getUserByName(String fName, String lName){
        ArrayList<User> result = new ArrayList<>();

        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM users WHERE first_name = ? AND last_name = ?");
            preparedStatement.setString(1,fName);
            preparedStatement.setString(2,lName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
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

    public List<User> getUserByBirthPlace (String birthPlace){
        ArrayList<User> result = new ArrayList<>();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM users WHERE birth_place = ?");
            preparedStatement.setString(1,birthPlace);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
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

    public List<User> getUserByBirthDate (String birthDate){
        ArrayList<User> result = new ArrayList<>();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM users WHERE birth_date = ?");
            preparedStatement.setString(1,birthDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
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

    public List<User> getUserByAddress (String adr){
        ArrayList<User> result = new ArrayList<>();
        connect = CoonectionConfiguration.getConnection();
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM users WHERE address = ?");
            preparedStatement.setString(1,adr);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(setUserFromResult(resultSet));
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
            preparedStatement.setBlob(7,user.getFingerPrint());
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

    public InputStream getImageFromBlob(Blob blob){
        InputStream binaryStream = null;
        try {
            binaryStream = blob.getBinaryStream(0,blob.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return binaryStream;
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
}

