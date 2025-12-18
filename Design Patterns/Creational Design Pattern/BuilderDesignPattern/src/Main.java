public class Main{
    public static void main(String[] args){
        Person p1 = new Person
                .Builder(1001, "Alex")
                .setEmail("Alex@gmail.com")
                .setMobileNumber(1234567890).build();
        System.out.println(p1);

        Person p2 = new Person
                .Builder(1002, "Benson")
                .setEmail("Benson@gmail.com")
                .setMobileNumber(1234567891)
                .build();
        System.out.println(p2);
    }
}