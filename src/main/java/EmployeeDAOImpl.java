import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {


    private Connection connection;

    public EmployeeDAOImpl() {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        // В ресурсах блока try создаем объект сессии с помощью нашего конфиг-файла
        // И открываем сессию
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            // вызываем на объекте сессии метод save
            // данный метод внутри себя содержит необходимый запрос к базе
            // для создания новой строки
            session.save(employee);
            // Выполняем коммит, то есть сохраняем изменения,
            // которые совершили в рамках транзакции
            transaction.commit();
        }

    }
//    @Override
//    public void create(Employee employee) {
//        // Формируем запрос к базе с помощью PreparedStatement
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "INSERT INTO employee (first_name, last_name, gender, city_id) VALUES ((?), (?), (?), (?))")) {
//
//            // Подставляем значение вместо wildcard
//            // первым параметром указываем порядковый номер wildcard
//            // вторым параметром передаем значение
//            preparedStatement.setString(1, employee.getFirst_name());
//            preparedStatement.setString(2, employee.getLast_name());
//            preparedStatement.setString(3, employee.getGender());
//            preparedStatement.setInt(4, employee.getCity().getCity_id());
//
//            // С помощью метода executeQuery отправляем запрос к базе
////            preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        // С помощью конфиг-файла получаем сессию, открываем ее
        // и через метод get получаем объект
        // В параметре метода get нужно указать объект какого класса нам нужен
        // и его id
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);

    }
//    @Override
//    public Employee readById(int id) {
//        Employee employee = new Employee();
//        // Формируем запрос к базе с помощью PreparedStatement
//        try (PreparedStatement statement = connection.prepareStatement(
//                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND employee_id=(?)")) {
//
//            // Подставляем значение вместо wildcard
//            statement.setInt(1, id);
//
//            // Делаем запрос к базе и результат кладем в ResultSet
//            ResultSet resultSet = statement.executeQuery();
//
//            // Методом next проверяем есть ли следующий элемент в resultSet
//            // и одновременно переходим к нему, если таковой есть
//            while (resultSet.next()) {
//
//                // С помощью методов getInt и getString получаем данные из resultSet
//                // и присваиваем их полим объекта
//                employee.setId(Integer.parseInt(resultSet.getString("employee_id")));
//                employee.setFirst_name(resultSet.getString("first_name"));
//                employee.setLast_name(resultSet.getString("last_name"));
//                employee.setGender(resultSet.getString("gender"));
//                employee.setCity(new City(resultSet.getInt("city_id"),
//                        resultSet.getString("city_name")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employee;
//    }

    @Override
    public List<Employee> readAll() {
        List<Employee> users = (List<Employee>) HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Employee").list();
        return users;
    }
//    @Override
//    public List<Employee> readAll() {
//        // Создаем список, в который будем укладывать объекты
//        List<Employee> employeeList = new ArrayList<>();
//
//        try (PreparedStatement statement = connection.prepareStatement(
//                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id")) {
//
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//
//                int id = Integer.parseInt(resultSet.getString("id"));
//                String first_name = resultSet.getString("first_name");
//                String last_name = resultSet.getString("last_name");
//                String gender = resultSet.getString("gender");
//                City author = new City(resultSet.getInt("city_id"),
//                        resultSet.getString("city_name"));
//
//
//                // Создаем объекты на основе полученных данных
//                // и укладываем их в итоговый список
//                employeeList.add(new Employee(id, first_name, last_name, gender, author));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return employeeList;
//    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Для обновления данных нужно передать в конструктор
            // объект с актуальными данными
            session.update(employee);
            transaction.commit();
        }

    }
//    @Override
//    public void updateEmployeeById(int id) {
//        try (PreparedStatement statement = connection.prepareStatement(
//                "UPDATE employee WHERE id=(?)")) {
//
//            statement.setInt(1, id);
//            statement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void delete(Employee employee) {
            try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                // Для удаления объекта из таблицы нужно передать его в метод delete
                session.delete(employee);
                transaction.commit();
            }
    }
//    @Override
//    public void deleteById(int id) {
//        try (PreparedStatement statement = connection.prepareStatement(
//                "DELETE FROM employee WHERE id=(?)")) {
//
//            statement.setInt(1, id);
//            statement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
