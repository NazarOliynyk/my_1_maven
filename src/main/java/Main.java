import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class Main {

public static void main(String[]args) throws SQLException{
    Connection connection = DriverManager.getConnection (
            "jdbc:mysql://127.0.0.1:3306/", //localhost might be instead of 127.0.0.1
            "root",                        // .../databaseName - to get a certain DB
            "2419839"
    );
    createAndUseDb(connection, "MyDB_From_Maven");
    //createTable(connection, "users_Data");
    //addUser(connection, "users_Data", "Nazar", "Oliynyk", 40);
    //addUser(connection, "users_Data", "Ihor", "Vasyta", 57);
    //addUser(connection, "users_Data", "Yura", "Stanko", 38);
    //addUser(connection, "users_Data", "Sergij", "Danevich", 32);

    System.out.println(selectByAge(connection, "users_Data", 40));
    System.out.println(selectByAge(connection, "users_Data", 2));

    createAndUseDb(connection, "books_db");
    //createTable(connection, "users_Data");

    // taking info from a DB created before
    List<Book> books = findAllBooks(connection, "books");
    for (Book book : books) {
        System.out.println(book);
    }

    }

    private static void createAndUseDb (Connection connection, String name) throws SQLException{

        PreparedStatement statement = connection
                .prepareStatement("create database if not exists "+name+" character set 'utf8'");

            statement.executeUpdate();
            statement.execute("use "+name);
    }

    private static void createTable(Connection connection, String name) throws SQLException{

        PreparedStatement statement = connection
                .prepareStatement("create table if not exists "+name +
                        "(id int primary key auto_increment," +
                        "userName varchar (45),"+
                        "userSurname varchar(45),"+
                        "userAge int) ;");
        statement.executeUpdate();
    }

    private static void addUser
            (Connection connection, String table, String name, String surname, int age)
            throws SQLException{
    PreparedStatement statement = connection
            .prepareStatement("insert into "+table+
                    "(userName, userSurname, userAge) values (?, ?, ?);");
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setInt(3, age);
        statement.executeUpdate();
    }

    public static User selectByAge
            (Connection connection, String tabel, int age) throws SQLException{

    PreparedStatement statement = connection
            .prepareStatement("select *from "+tabel+" where userAge = "+age+";");
    ResultSet set= statement.executeQuery();

    User user=null;
    while (set.next()){

        int id = set.getInt("id");
        String userName = set.getString("userName");
        String userSurname= set.getString("userSurname");
        int userAge = set.getInt("userAge");
        user = new User(id, userName, userSurname, userAge);
       }
        return user;
    }

    public static List<Book> findAllBooks(Connection connection, String table) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "select *from "+table+ ";");
        ResultSet resultSet = statement.executeQuery();
        List<Book> books = new ArrayList<Book>();
        while (resultSet.next()){
            int book_id = resultSet.getInt(1);
            String book_name = resultSet.getString(2);
            int price = resultSet.getInt(3);
            int publisher_id = resultSet.getInt(4);
            books.add(new Book(book_id, book_name, price, publisher_id));
        }
        return books;
    }

}
