package BDD.resources;

import NewProjects.Serialization_and_Deserialization.Serialization.Address;
import NewProjects.Serialization_and_Deserialization.Serialization.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public Address addPlacePayload(String name, String language, String address){
        Address a=new Address();
        a.setAccuracy(50);
        a.setName(name);
        a.setPhone_number("(+91) 983 893 3937");
        a.setAddress(address);

        a.setLanguage(language);
        a.setWebsite("http://google.com");
        List<String> myList=new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        a.setLocation(l);
        a.setTypes(myList);
        return a;
    }
}
