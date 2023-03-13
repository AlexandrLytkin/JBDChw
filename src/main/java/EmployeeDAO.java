import java.util.List;

public interface EmployeeDAO {
    // Добавление объекта
    Employee create(Employee employee);

    // Получение объекта по id
    Employee readById(int id);

    // Получение всех объектов
    List<Employee> readAll();

    // Изменение объекта по id
    void updateEmployee(Employee employee);
//    void updateEmployeeById(int id);

    // Удаление объекта по id
    void delete(Employee employee);
//    void deleteById(int id);
}
