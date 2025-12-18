public class Main{
    public static void main(String[] args){
        Publisher publisher = new Publisher();
        Subscriber s1 = new Subscriber("Alex");
        Subscriber s2 = new Subscriber("Benson");
        publisher.attach(s1);
        publisher.attach(s2);
        publisher.setMessage("Albert Camus's books are in stock!!!");
    }
}