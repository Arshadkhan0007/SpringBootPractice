import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args){
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alex", 1500000),
                new Employee("Benson", 2500000),
                new Employee("Dricus", 2700000),
                new Employee("Charles", 1500000)
        );

        Company company = new Company(employeeList);
        Iterator<Employee> iterator = company.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next() + "\n");
        }
    }
}