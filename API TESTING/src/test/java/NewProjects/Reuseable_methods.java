package NewProjects;

import io.restassured.path.json.JsonPath;

public class Reuseable_methods {

    public static JsonPath rawToJson(String response){
        JsonPath js1=new JsonPath(response);
                return js1;
    }
}
