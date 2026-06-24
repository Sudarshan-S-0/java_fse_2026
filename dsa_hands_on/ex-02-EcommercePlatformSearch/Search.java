//import java.util.*;
class Product{
    int productId;
    String productName;
    String category;
    Product(int productId, String productName, String category){
        this.productId = productId;
        this.productName= productName;
        this.category = category;
    }
}
public class Search {

    static int linearSearch(Product products[], int targetId){
        for(int i=0;i<products.length;i++){
            if(products[i].productId==targetId){
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(Product products[], int targetId){
        int l=0, h=products.length-1;
        while(l<=h){
            int m = (l+h)/2;
            if (products[m].productId == targetId)
                return m;
            else if (products[m].productId < targetId)
                l= m+1;
            else
                h= m-1;
        }
        return -1;
    }

    public static void main(String[] args){
        Product products[] = {
            new Product(101, "Mobile phone","Electronics"),
            new Product(102,"Air Conditioner","Electronics"),
            new Product(103,"Refrigerator","Electronics"),
            new Product(104,"Keyboard","Electronics")
        };

        System.out.println("Linear Search:");
        int res1 = linearSearch(products,103);

        if(res1!=-1)
            System.out.println(products[res1].productName);
        else
            System.out.println("Product not found");

        System.out.println("Binary Search:");
        int res2 = binarySearch(products,104);

        if(res2!=-1)
            System.out.println(products[res2].productName);
        else
            System.out.println("Product not found");
    }
}
