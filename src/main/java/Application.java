import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application{

    public static void main(String[] args) throws SQLException {

//        final String user = "postgres";
//        final String password = "3708";
//        final String url = "jdbc:postgresql://localhost:5432/postgres";


        // Создаем объект класса ДАО
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee employee1 = new Employee("Katya","Alexeevna","woman",20,1);
        // Создаем объект
        employeeDAO.create(employee1);

        // Получаем объект по id
        System.out.println(employeeDAO.readById(1));

        // Получаем полный список объектов
        List<Employee> list = employeeDAO.readAll();

        for (Employee employee : list) {
            System.out.println(employee);
        }

        Employee employee2 = new Employee(11,"Nastya", "Olegovna", "woman",20,1);

        // Изменяем объект
        employeeDAO.updateEmployee(employee2);

        // Удаляем объект
        employeeDAO.delete(employee2);


//        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
//            String sql = "SELECT * FROM city INNER JOIN employee ON city.city_id = employee.city_id WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setInt(1, id_person);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String first_name = resultSet.getString("first_name");
//                String last_name = resultSet.getString("last_name");
//                String gender = resultSet.getString("gender");
//                String city = resultSet.getString("city_name");
//
//                System.out.println(id + " " + first_name + " " + last_name + " " + gender+" "+city);
//            }
//        }

//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//
//            // Создаем объект класса EmployeeDAOImpl
//            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
//
//            City city = new City(1, "Moskov");
//            Employee employee1 = new Employee("Igor","Igorevich","man", city);
//
//            // Вызываем метод добавления объекта
////            employeeDAO.create(employee1);
//
////            employeeDAO.deleteById(10);
//            // Создаем список наполняя его объектами, которые получаем
//            // путем вызова метода для получения всех элементов таблицы
//            List<Employee> list = new ArrayList<>(employeeDAO.readAll());
//
//            // Выведем список в консоль
//            for (Employee employee : list) {
//                System.out.println(employee);
//            }
//
//
//        }
    }
}
