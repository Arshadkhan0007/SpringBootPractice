import java.util.List;

public class Company implements Aggregate<Employee>{

    private List<Employee> employeeList;

    public Company(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public Iterator<Employee> createIterator() {
        return new EmployeeIterator(employeeList);
    }
}
