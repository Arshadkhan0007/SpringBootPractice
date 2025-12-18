public class Main{
    public static void main(String[] args){
        Game client = new Game();

        client.createZombie().displayStats();
        client.createSkeleton().displayStats();
    }
}