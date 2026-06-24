public class Forecast {
    static double forecast(double currVal, double growthRate, int yrs){
        if(yrs==0) return currVal;
        else return forecast(currVal, growthRate, yrs-1)*(1+growthRate);
    }
    public static void main(String[] args){
        double res = forecast(1000,0.10,3);
        System.out.println("Future value: "+res);
    }
    
}
