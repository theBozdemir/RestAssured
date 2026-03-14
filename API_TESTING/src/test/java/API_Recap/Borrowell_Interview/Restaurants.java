package API_Recap.Borrowell_Interview;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class Restaurants {
    GetRestaurants getRest;
    ArrayList<Data> restaurants = new ArrayList<Data>();
    @Test
    public void bestRestaurants() {
        RestAssured.baseURI = "https://jsonmock.hackerrank.com";
        for (int i = 0; i < 40; i++) {
            getRest =
                    given()
                            .queryParams("city", "Seattle")
                            .queryParams("page", i + 1)
                            .when().get("/api/food_outlets")
                            .then().extract().body().as(GetRestaurants.class);
            if (getRest.getData().size() > 0) {
                for (int j = 0; j < getRest.getData().size(); j++) {
                    restaurants.add(getRest.getData().get(j));

                }
            }
        }
        for (int i=0; i< restaurants.size()-1;i++){
            for(int j=0;j<restaurants.size()-i-1; j++){
                double rating1=restaurants.get(j).getUser_rating().getAverage_rating();
                double rating2=restaurants.get(j+1).getUser_rating().getAverage_rating();
                if(rating1<rating2){
                    Data temp= restaurants.get(j);
                    restaurants.set(j,restaurants.get(j+1));
                    restaurants.set(j+1,temp);


                }
            }

        }
        //Best 10 restaurants
        for(int i=0;i<10;i++){
            System.out.println(i+1+". "+restaurants.get(i).getName()+" : "+restaurants.get(i).getUser_rating().getAverage_rating());
        }
    }
}
