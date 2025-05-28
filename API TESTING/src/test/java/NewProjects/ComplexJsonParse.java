package NewProjects;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.coursePrice());
        int sum=0;

        //Print Number Of Courses
        int numberCourse=js.getInt("courses.size()"); //implement this when you have a array inside a json file
        System.out.println("Number of courses ="+numberCourse);
        //Print Total Amount
        int totalAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println("Total Amount ="+totalAmount);
        //Print Title of the first course
        for(int i=0; i<numberCourse;i++){
            String courseTitle = js.getString("courses[" + i + "].title");
            System.out.println((i+1)+". course title is ="+courseTitle);
        }
        //Total price comparison
        for(int i=0; i<numberCourse;i++){

            int price=js.getInt("courses["+i+"].price");
            int multiplier= js.getInt("courses["+i+"].copies");
            int bookCost=price*multiplier;
            sum+=bookCost;
        }
        System.out.println("Total calculated book price is "+sum);
        Assert.assertEquals(totalAmount,sum);




    }
}
