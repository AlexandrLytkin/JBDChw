import java.util.List;

public interface EmployeeDAO {
    // Добавление объекта
    void create(Employee employee);
    // Получение объекта по id
    Employee readById(int id);
    // Получение всех объектов
    List<Employee> readAll();
    // Изменение объекта по id
    void updateEmployeeById(int id);
    // Удаление объекта по id
    void deleteById(int id);
}
