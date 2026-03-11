package API_Recap.WeatherAPIFromLocal;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestRunner_LocalWeather {
    @Test
    public void getWeather(){
        RestAssured.baseURI="http://localhost:8080";
        List<CityInformation> info =given().when().get("/data").then().extract().response().jsonPath().getList(".",CityInformation.class);
        List<CityInformation> copyList=new ArrayList<>();
        for (int i=0; i<info.size();i++){
            copyList.add(info.get(i));
        }


        for(int i=0; i< copyList.size()-1;i++){
            for(int j=0;j<copyList.size()-i-1;j++){
                if(copyList.get(j).getWeather().getTemp_c()<copyList.get(j+1).getWeather().getTemp_c()){
                    CityInformation temp=copyList.get(j);
                    copyList.set(j,info.get(j+1));
                    copyList.set((j+1),temp);
                }

            }
           // System.out.println("City of "+info.get(i).getCity()+" temperature is "+info.get(i).getWeather().getTemp_c());

        }
        for (int a=0; a<copyList.size();a++){
            System.out.println("City of "+copyList.get(a).getCity()+" and temperature is : "+copyList.get(a).getWeather().getTemp_c());
        }
    }
}
