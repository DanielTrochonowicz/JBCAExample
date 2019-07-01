package jdbc_example;

import java.sql.*;
import java.util.Random;

public class Users {
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/jdbc_example?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "flatron21");

            Statement statement = conn.createStatement();

            String createTable = "CREATE TABLE if not exists USERS (\n"+
                    "id int NOT NULL AUTO_INCREMENT,\n" +
                    "FIRST_NAME VARCHAR(255),\n" +
                    "LAST_NAME VARCHAR(255),\n" +
                    "Age INT,\n" +
                    "PRIMARY KEY (id))";
              int resultSetUpdate = statement.executeUpdate(createTable);


            String insert1 = "insert into users(first_name, last_name,age) " +
                             "values ('Daniel', 'Trochonowicz', 30)";
            int resultSetUpdate1 = statement.executeUpdate(createTable);


            String parm_query = "insert into users(first_name, last_name,age) values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(parm_query);
            preparedStatement.setString(1,"Michał");
            preparedStatement.setString(2, "Nowak");
            preparedStatement.setInt(3,new Random().nextInt(100));

            String del = "delete from users where id = 11";
            int rs4 = statement.executeUpdate(del);
            int r2 = preparedStatement.executeUpdate();

            String writeAll = "select * from users";
            ResultSet resultSet = statement.executeQuery(writeAll);
            System.out.println("Zawartość tabeli: ");
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);

                System.out.println(id + " : " + firstName + " : " + lastName + " : " + age );
            }

            conn.close();

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getErrorCode());
        }
    }
}
