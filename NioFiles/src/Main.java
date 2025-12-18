import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main{
    public static void main(String[] args){
        //CREATING A FILE
        Path path = Paths.get("demo.txt");
//
//        try {
//            Files.createFile(path);
//        } catch(IOException ex){
//            System.out.println("Exception thrown by createFile() method, " + ex.getMessage());
//        }

        //WRITING INTO A FILE
//        try {
//            Files.write(path, "Hello World!".getBytes(), StandardOpenOption.APPEND);
//            List<String> lines = new ArrayList<>(List.of(
//                    "In the bleak mid-winter",
//                    "frosty wind made me moan",
//                    "Earth stood as iron",
//                    "and water as stone"
//            ));
//            Files.write(path, lines, StandardOpenOption.APPEND);
//        } catch (IOException ex){
//            System.out.println("Exception thrown by write() method, " + ex.getMessage());
//        }

        //READING A FILE
//        try {
//            List<String> lines = Files.readAllLines(path);
//
//            System.out.println("Using realAllLines() method");
//            lines.stream().forEach(System.out::println);
//
//            System.out.println("---------------------------------------");
//
//            System.out.println("Using lines() method");
//            Files.lines(path).forEach(System.out::println);
//        } catch (IOException ex){
//            System.out.println("Exception thrown by readAllLines() method, " + ex.getMessage());
//        }

        //CREATING A DIRECTORY
//        Path directoryPath = Paths.get("my_folder.txt");
//        try {
//            Files.createDirectory(directoryPath);
//        } catch(IOException ex){
//            System.out.println("Exception thrown by createDirectory() method, " + ex.getMessage());
//        }

        //CREATING A NESTED DIRECTORY
//        Path nestedDirectoryPath = Paths.get("parent/child/grandchild");
//        try{
//            Files.createDirectories(nestedDirectoryPath);
//        } catch (IOException ex){
//            System.out.println("Exception thrown by createDirectories() method, " + ex.getMessage());
//        }

        //MOVING A FILE
        Path source = Paths.get("demo.txt");
        Path destination = Paths.get("my_folder/demo.txt");
//
//        try {
//            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ex){
//            System.out.println("Exception thrown by move() method, " + ex.getMessage());
//        }

        //COPYING A FILE
        source = Paths.get("my_folder.txt/demo.txt");
        destination = Paths.get("parent/child/grandchild/demo.txt");

//        try {
//            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ex){
//            System.out.println("Exception thrown by copy() method, " + ex.getMessage());
//        }

        //DELETING A FILE
        Path filePath = Paths.get("dummy.txt");
//        try {
//            Files.createFile(filePath);
//        } catch(IOException ex){
//            System.out.println("Exception thrown by createFile() method");
//        }

//        try{
//            Files.delete(filePath);
//        } catch (IOException ex) {
//            System.out.println("Exception thrown by delete() method, " + ex.getMessage());
//        }
    }
}