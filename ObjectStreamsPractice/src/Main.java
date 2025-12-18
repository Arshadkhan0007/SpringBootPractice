import java.io.*;

class Main{
    public static void main(String[] args) {

        //Using ObjectOutputStream
        Student student1 = new Student(101, "Alex", 37.5);
        try(FileOutputStream fileOut = new FileOutputStream("student.obj");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){

            objOut.writeObject(student1);

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        //Using ObjectInputStream
        try(FileInputStream fileIn = new FileInputStream("student.obj");
            ObjectInputStream objIn = new ObjectInputStream(fileIn)){

            Student student2 = (Student) objIn.readObject();
            System.out.println(student2);

        } catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}