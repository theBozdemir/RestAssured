package API_Recap;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;

public class ParsingComplexJSON {
    public static void main(String[] args) {
        JsonPath js=new JsonPath(Payload.coursePrice());
        int courseNumber=js.getInt("courses.size()");
        System.out.println("Number of courses are ="+courseNumber);

        int purchaseAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println("Puchase amount is :"+purchaseAmount);

        //First course title
        String firstCourse=js.getString("courses[0].title");
        System.out.println("First course title is : "+firstCourse);
        int total=0;

        for(int i =0; i<courseNumber;i++){
            int courseTotal=0;
            String courseName=js.getString("courses["+i+"].title");
            int coursePrice=js.getInt("courses[+"+i+"].price");
            int copies=js.getInt("courses[+"+i+"].copies");
            courseTotal=coursePrice*copies;
            System.out.println("Course name is : "+courseName+" and course price is : "+coursePrice);
            total=total+courseTotal;

        }
        String searchedString="RPA";
        int i=0;
        while(i<courseNumber){
            if(js.getString("courses["+i+"].title").equals(searchedString)){
                System.out.println("Number of copies of "+searchedString+" class is :"+js.getInt("courses["+i+"].copies"));
                break;
            }
                i++;
            }
        Assert.assertEquals(js.getInt("dashboard.purchaseAmount"),total);
        System.out.println("Calculated total purchase amount is = "+total);
        }
    }

