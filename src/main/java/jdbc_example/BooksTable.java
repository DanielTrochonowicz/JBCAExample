package jdbc_example;

import java.sql.*;

public class BooksTable {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/jdbc_example?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "flatron21");
            Statement statement = connection.createStatement();

            // pobranie wszystkich ksiązek
            String allBooks = "Select * from books";
            System.out.println("Write the books: " + allBooks);
            ResultSet resultSet = statement.executeQuery(allBooks);
            printBooks(resultSet);

            // wypisz ksiązki ilość > 5
            System.out.println("wypisz ksiązki ilość > 5");
            String sql = "Select * from books where quantity > 5";
            ResultSet resultSet1 = statement.executeQuery(sql);
            printBooks(resultSet1);

            // wypisz ksiązki ilość > 3 i mniejsza cena niż 40
            System.out.println("wypisz ksiązki ilość > 3 i mniejsza cena niż 40");
            String sql2 = "Select * from books where quantity > 3 and price < 40";
            ResultSet resultSet2 = statement.executeQuery(sql2);
            printBooks(resultSet2);

            //wyszukiwanie z parametrami
            String query = "select * from books where quantity > ? and price < ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 40);
            ResultSet resultSet3 = preparedStatement.executeQuery();
            printBooks(resultSet3);


            //UPDATE
            // Zaktualizuj tytuł dla najmniejszego id

//            String sql3 = "update books set title = 'nowa pozycja', author = 'Carroll', price = 28.79, quantity = 3 order by id asc limit 1;";
//            int ulepszenie = statement.executeUpdate(sql3);
//
            String query2 = "update books set title = ? order by id asc limit 1";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
            preparedStatement1.setString(1, "Komórka");
            int rs4 = preparedStatement1.executeUpdate();
            System.out.println("Zaktualizuj tytuł dla najmniejszego id");
            printBooks();

            // Podnieś cenę wszystkich ksiązek o 10%
            String query3 = "update books set price = price * 1.1;";
            statement.executeUpdate(query3);
            System.out.println("Podnieś cenę wszystkich ksiązek o 10%");
            printBooks();

            // zwiększ liczbę sztuk o 2 dla danej ceny
            String query4 = "update books set quantity = quantity + 2 where price > ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query4);
            preparedStatement2.setFloat(1,50);
            preparedStatement2.executeUpdate();
            System.out.println("zwiększ liczbę sztuk o 2 dla danej ceny");
            printBooks();

            //INSERT
            // dodaj 3 ksiązki
            String query5 = "insert into books(title, author, price, quantity) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement3 = connection.prepareStatement(query5);
            preparedStatement3.setString(1, "Silmarillion");
            preparedStatement3.setString(2, "R.R. Tolkien");
            preparedStatement3.setFloat(3,40);
            preparedStatement3.setInt(4,0);
            preparedStatement3.executeUpdate();
            System.out.println("Dodano książkę");
            printBooks();

            // dodaj duplikat
            System.out.println("Dodawanie duplikatu");
            String query6 = "insert into books(id, title, author, price, quantity) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement4 = connection.prepareStatement(query6);
            preparedStatement4.setInt(1, 9);
            preparedStatement4.setString(2, "Silmarillion");
            preparedStatement4.setString(3, "R.R. Tolkien");
            preparedStatement4.setFloat(4, 40);
            preparedStatement4.setInt(5, 0);
            preparedStatement4.executeUpdate();
            printBooks();

            // usuwanie
            String query7 = "delete from books where quantity = ?";
            PreparedStatement preparedStatement5 = connection.prepareStatement(query7);
            preparedStatement5.setInt(1,0);
            preparedStatement5.executeUpdate();
            System.out.println("Usunięto książkę");
            printBooks();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());

        }
    }
    public static void printBooks(ResultSet resultSet) throws SQLException {

        int rowCount = 0;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            int quantity = resultSet.getInt("quantity");
           // rowCount++;
            System.out.println(id + ", " + title + ", " + author + ", " + price + ", " + quantity);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    public static void printBooks() throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/jdbc_example?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "flatron21");
        Statement statement = connection.createStatement();
        String str1 = "select * from books";
        ResultSet resultSet = statement.executeQuery(str1);

        int rowCount = 0;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            int quantity = resultSet.getInt("quantity");
           // rowCount++;
            System.out.println(id + ", " + title + ", " + author + ", " + price + ", " + quantity);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }
}
