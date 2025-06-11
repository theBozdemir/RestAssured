package OldProjects.Day6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/*public class SerializationDeserialization {

   /* //@Test
    void serialization() throws JsonProcessingException { // POJO --------> JSON
        RestAssured.baseURI="http://localhost:3000";
        //We created java object using pojo class
        POJO student =new POJO();
        //student.setName("Tarik");
        student.setID("1613");
        //student.setLocation("Canada");
        student.setPhone("4167222716");
        String courseArr[]= {"C", "C++"};
        student.setCourse(courseArr);
        //converting java object to json object
        ObjectMapper objMapper = new ObjectMapper();
        String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(jsonData);

        given()
                .contentType("application/json")
                .when()
                .post("/students");







    }
    //@Test
    void deserialization() throws JsonProcessingException { // JSON -------> POJO
        String jsonData = "{\n" +
                "  \"name\" : \"Tarik\",\n" +
                "  \"location\" : \"Canada\",\n" +
                "  \"phone\" : \"4167222716\",\n" +
                "  \"course\" : [ \"C\", \"C++\" ],\n" +
                "  \"id\" : \"1613\"\n" +
                "}";

        //Converting Json data into POJO Class Object
        ObjectMapper stuOBJ =new ObjectMapper();
        POJO ObjPojo=stuOBJ.readValue(jsonData, POJO.class); // this will return student class object
        //String name = ObjPojo.getName();
        //String location = ObjPojo.getLocation();
        String id = ObjPojo.getID();
        String phone = ObjPojo.getPhone();
        String course1= ObjPojo.getCourse()[0];
        String course2= ObjPojo.getCourse()[1];
        System.out.println("Name of student is "+name+", Location of student is : "+location+", id of Student is : "+id+", Student phone number is: "+phone+", Student's 1.st course is: "+course1+", Student's 2nd course is : "+course2);


    }
}
*/