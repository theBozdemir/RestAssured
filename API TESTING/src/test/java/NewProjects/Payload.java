package NewProjects;

public class Payload {

        public static String coursePrice(){
            return"{\n" +
                    "\n" +
                    "\"dashboard\": {\n" +
                    "\n" +
                    "\"purchaseAmount\": 910,\n" +
                    "\n" +
                    "\"website\": \"rahulshettyacademy.com\"\n" +
                    "\n" +
                    "},\n" +
                    "\n" +
                    "\"courses\": [\n" +
                    "\n" +
                    "{\n" +
                    "\n" +
                    "\"title\": \"Selenium Python\",\n" +
                    "\n" +
                    "\"price\": 50,\n" +
                    "\n" +
                    "\"copies\": 6\n" +
                    "\n" +
                    "},\n" +
                    "\n" +
                    "{\n" +
                    "\n" +
                    "\"title\": \"Cypress\",\n" +
                    "\n" +
                    "\"price\": 40,\n" +
                    "\n" +
                    "\"copies\": 4\n" +
                    "\n" +
                    "},\n" +
                    "\n" +
                    "{\n" +
                    "\n" +
                    "\"title\": \"RPA\",\n" +
                    "\n" +
                    "\"price\": 45,\n" +
                    "\n" +
                    "\"copies\": 10\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "]\n" +
                    "\n" +
                    "}";
    }
        public static String book(String isbn, String aisle){
           String body= "    {\n" +
                    "        \"name\": \"Appium\",\n" +
                    "        \"isbn\": \""+isbn+"\",\n" +
                    "        \"aisle\": \""+aisle+"\",\n" +
                    "        \"author\": \"John Doe\"\n" +
                    "    }";
           return body;
    }
}
