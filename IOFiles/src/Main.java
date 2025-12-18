import java.io.*;
import java.util.Arrays;

public class Main{
    private static File destination;

    public static void main (String[] args){
        //CREATING A FILE
        File file = new File("demo.txt");
//        try {
//            if(file.createNewFile()){
//                System.out.println(file.getName() + " has been created");
//            } else{
//                System.out.println(file.getName() + " couldn't be created");
//            }
//        } catch(IOException ex){
//            System.out.println("Exception thrown by createNewFile() method, " + ex.getMessage());
//        }

        //WRITING INTO A FILE
        //USING FileWriter
//        try(FileWriter writer = new FileWriter(file)){
//            writer.write("First Line\n");
//            writer.write("Second Line");
//        } catch (IOException ex){
//            System.out.println("Exception occurred while writing into a file using FileReader, " + ex.getMessage());
//        }

        //Using BufferedWriter
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
//            writer.newLine();
//            writer.write("Third Line");
//            writer.newLine();
//            writer.write("Fourth Line");
//        } catch(IOException ex){
//            System.out.println("Exception occurred while writing into a file using BufferedWriter, " + ex.getMessage());
//        }

        //READING A FILE USING BufferedReader
//        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
//            String line;
//            while((line = reader.readLine()) != null){
//                System.out.println(line);
//            }
//        } catch(IOException ex){
//            System.out.println("Exception occurred while reading a file using BufferedReader, " + ex.getMessage());
//        }

        //CREATING DIRECTORY
        File directory = new File("my_folder");
//        if(directory.mkdir()){
//            System.out.println(directory.getName() + " directory has been created");
//        } else {
//            System.out.println(directory.getName() + " directory couldn't be created");
//        }

        //CREATING DIRECTORIES
        File nestedDirectories = new File("Parent/Child/Grandchild");
//        if(nestedDirectories.mkdirs()){
//            System.out.println(nestedDirectories.getName() + " directories have been created");
//        } else {
//            System.out.println(nestedDirectories.getName() + " directories have been created");
//        }

        //RENAMING A FILE
        File source = new File("demo.txt");
        File renamedSource = new File("Demo.txt");
//
//        if(source.renameTo(renamedSource)){
//            System.out.println("File renamed successfully");
//        } else {
//            System.out.println("Couldn't rename the file");
//        }

        //MOVING A FILE
        source = renamedSource;
        File destination = new File("Parent/Child/Grandchild/Demo.txt");

//        try(BufferedReader br = new BufferedReader(new FileReader(source));
//            BufferedWriter bw = new BufferedWriter(new FileWriter(destination))){
//
//            String line;
//            while((line = br.readLine()) != null){
//                bw.write(line);
//            }
//            source.delete();
//
//        } catch (IOException ex){
//            System.out.println("Exception occurred when creating BufferedReader or BufferedWriter, " + ex.getMessage());
//        }


        //DELETING A FILE
//        File dummyFile = new File("dummyFile");
//        try{
//            dummyFile.createNewFile();
//        } catch (IOException ex){
//            System.out.println("Exception thrown by createNewFile() method, " + ex.getMessage());
//        }
//
//        if(dummyFile.delete()){
//            System.out.println("File has been deleted successfully");
//        } else {
//            System.out.println("File couldn't be deleted");
//        }

        //COPYING DIRECTORIES
        source = new File("Parent");
        destination = new File("my_folder");
        copyDirectory(source, destination);

        //DELETING DIRECTORY
        source = new File("my_folder");
        deleteDirectory(source);
    }

    public static void copyDirectory(File source, File destination){
        if(source.isDirectory()){
            if(!destination.exists()){
                destination.mkdirs();
            }

            String[] children = source.list();
            if(children != null){
                Arrays.stream(children)
                        .forEach(child -> {copyDirectory(new File(source, child), new File(destination, child));});
            }
        }
    }

    public static void deleteDirectory(File dir){
        if(dir.isDirectory()){
            File[] children = dir.listFiles();
            if(children != null) {
                Arrays.stream(children).forEach(Main::deleteDirectory);
            }
        }
        dir.delete();
    }
}