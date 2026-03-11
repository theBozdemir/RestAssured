package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    public static void main(String[] args) {
        List<Integer>numbers=new ArrayList<>();
        numbers.add(10);
        numbers.add(7);
        numbers.add(5);
        numbers.add(3);
        numbers.add(4);
        numbers.add(6);
        numbers.add(1);
        numbers.add(8);
        numbers.add(9);
        numbers.add(2);

        for(int i=0; i<numbers.size()-1;i++){
            for(int j=0; j<numbers.size()-i-1; j++){
                if(numbers.get(j)>numbers.get(j+1)){
                    int temp=numbers.get(j);
                    numbers.set(j,numbers.get(j+1));
                    numbers.set(j+1,temp);
                }
            }
        }
        for (int i=0;i<numbers.size();i++){
            System.out.println(numbers.get(i));
        }
        int[] numberArray ={2,3,4,5,6,9,8,7,1,10};
        System.out.println("Array length is : "+numberArray.length);
        for(int i =0;i<numberArray.length;i++){
            for(int j=0; j<numberArray.length-i-1;j++){
                if (numberArray[j]>numberArray[j+1]){
                    int temp=numberArray[j];
                    numberArray[j]=numberArray[j+1];
                    numberArray[j+1]=temp;
                }
            }
        }
        for (int i=0; i<numberArray.length;i++){
            if(i!=numberArray.length-1){
                System.out.print(numberArray[i]+" , ");
            }
            else {
                System.out.println(numberArray[i]+".");
            }
        }


    }
}
