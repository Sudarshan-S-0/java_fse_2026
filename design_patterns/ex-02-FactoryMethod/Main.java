//package design_patterns.ex-02-FactoryMethod;
interface Document{
    void open();
}

class WordDocument implements Document{
    public void open() {
        System.out.println("Opening word doc");
    }
}
class PdfDocument implements Document{
    public void open() {
        System.out.println("Opening pdf doc");
    }
}
class ExcelDocument implements Document{
    public void open(){
        System.out.println("Opening excel doc");
    }
}

abstract class DocumentFactory{
    public abstract Document createDocument();
}

class WordFactory extends DocumentFactory{
    public Document createDocument(){
        return new WordDocument();
    }
}
class PdfFactory extends DocumentFactory{
    public Document createDocument(){
        return new PdfDocument();
    }
}
class ExcelFactory extends DocumentFactory{
    public Document createDocument(){
        return new ExcelDocument();
    }
}

public class Main {
    public static void main(String[] args){
        DocumentFactory factory;

        factory = new WordFactory();
        Document d1 = factory.createDocument();
        d1.open();
    
        factory = new PdfFactory();
        Document d2 = factory.createDocument();
        d2.open();
    
        factory = new ExcelFactory();
        Document d3 = factory.createDocument();
        d3.open();
    
    }
}