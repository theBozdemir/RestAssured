package API_Recap.BDD_Cucumber.StepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {



    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //Execute this code only when placeId is null
        StepDefinitions stepDef = new StepDefinitions();
        if (StepDefinitions.place_id == null) {
            stepDef.add_place_payload_with("Bozdemir", "Turkish", "NorthAmerica");
            stepDef.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepDef.verify_place_id_created_maps_to_using("Bozdemir", "getPlaceAPI");


        }
    }
}
