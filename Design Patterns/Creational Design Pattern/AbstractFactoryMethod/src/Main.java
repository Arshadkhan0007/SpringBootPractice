public class Main{
    public static void main(String[] args){
        FurnitureFactory modernFurniture = new ModernFurniture();
        Chair modernChair = modernFurniture.createChair();
        Table modernTable = modernFurniture.createTable();

        modernChair.sitOn();
        modernTable.putOn();

        FurnitureFactory victorianFurniture = new VictorianFurniture();
        Chair victorianChair = victorianFurniture.createChair();
        Table victorianTable = victorianFurniture.createTable();

        victorianChair.sitOn();
        victorianTable.putOn();
    }
}