package OldProjects.Day1.Day7;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {

    @Test
    void testGenerateData(){
        Faker faker=new Faker();
        String fullName=faker.name().fullName();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String password=faker.internet().password();
        String phoneNum=faker.phoneNumber().cellPhone();
        String email=faker.internet().safeEmailAddress();

        System.out.println(fullName+" "+firstName+" "+lastName+" "+username+" "+password+" "+phoneNum+" "+email);

    }
}
