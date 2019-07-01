package jdbc_example;

import java.sql.*;

public class People {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/jdbc_example?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "flatron21");

            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);

            String strSelect = "select * from people";
            System.out.println("The SQL query is: " + strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("The records selected are:");
            int rowCount = 0;
            while (rset.next()) {
                String job = rset.getString("job");
                double age = rset.getDouble("age");
                System.out.println(job + ", " + age);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

            Statement statement = conn.createStatement();
            String sql = "update people set job='artist' where id=1";
            int rowsAffected = statement.executeUpdate(sql);

            statement = conn.createStatement();
            sql = "delete from people where id=2";
            rowsAffected = statement.executeUpdate(sql);


            statement = conn.createStatement();
            sql = "INSERT INTO people(job,age) values ('specialist',79);\n;\n";
            rowsAffected = statement.executeUpdate(sql);
            conn.setAutoCommit(false);


            System.out.println("Prepared statement I:");
            String query = "select * from people where age > ? and job = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, 45);
            preparedStatement.setString(2,"accountant");



            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String job = rs.getString("job");
                double age = rs.getDouble("age");
                int jobIndex = rs.findColumn("job");
                System.out.println(job + ":" + age);
                System.out.println("Index Job: " + jobIndex);


                conn.commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
