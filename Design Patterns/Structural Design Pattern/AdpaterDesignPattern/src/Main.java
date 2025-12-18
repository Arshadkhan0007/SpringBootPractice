public class Main{
    public static void main(String[] args){
        LegacyPrinter legacyPrinter = new OldJetInkPrinter();
        PrinterAdapter printerAdapter = new PrinterAdapter(legacyPrinter);
        printerAdapter.print();

    }
}