package API_Recap.BDD_Cucumber.resources;

import API_Recap.Serialization.AddPlace;
import API_Recap.Serialization.LocationRecap;

import java.util.ArrayList;

public class TestDataBuild {
    public static AddPlace addPlacePayload(String name, String language, String address){
        AddPlace addPlace=new AddPlace();
        LocationRecap loc=new LocationRecap();
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setLanguage(language);
        addPlace.setAddress(address);
        addPlace.setWebsite("www.connexall.com");
        ArrayList<String> types=new ArrayList<String>();
        types.add("shop");
        types.add("shoe park");
        addPlace.setTypes(types);
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        addPlace.setLocation(loc);
        return addPlace;
    }

}
