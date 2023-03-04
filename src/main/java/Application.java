import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application{

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final String user = "postgres";
//        System.out.println("введите пароль :");
//        String s = scanner.nextLine();
//        final String password = s;
        final String password = "3708"; //s
        final String url = "jdbc:postgresql://localhost:5432/postgres";

        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM city INNER JOIN employee ON city.city_id = employee.city_id WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            System.out.println("чтобы получить сотрудника введи id от 1 до 6");
            int id_person = scanner.nextInt();
            preparedStatement.setInt(1, id_person);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String city = resultSet.getString("city_name");

                System.out.println(id + " " + first_name + " " + last_name + " " + gender+" "+city);
            }
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Создаем объект класса EmployeeDAOImpl
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City city = new City(1, "Moskov");
            Employee employee1 = new Employee("Omlet","Ovnovich","chicken", city);

            System.out.println("1");
            // Вызываем метод добавления объекта
            employeeDAO.create(employee1);

            System.out.println("2");
            // Создаем список наполняя его объектами, которые получаем
            // путем вызова метода для получения всех элементов таблицы
            List<Employee> list = new ArrayList<>(employeeDAO.readAll());

            System.out.println("3");
            // Выведем список в консоль
            for (Employee employee : list) {
                System.out.println(employee);
            }
        }
    }
}
