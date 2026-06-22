//package design_patterns.ex-01-singletonPattern;
//import java.util.*;
class Logger {
    private static Logger instance;

    private Logger(){
        System.out.println("Logger Created");
    }

    public static Logger getInstance(){
        if(instance==null){
            instance = new Logger();
        }
        return instance;
    }

    public void log(String m){
        System.out.println(m);
    }
}
public class Main{
    public static void main(String[] args){
        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();

        l1.log("Hello");
        l2.log("buddy");
        
        System.out.println("are the loggers l1 and l2 same? "+(l1==l2));
    }
}