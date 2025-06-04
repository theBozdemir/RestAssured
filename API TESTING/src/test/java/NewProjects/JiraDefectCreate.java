package NewProjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraDefectCreate {
    static{
        RestAssured.baseURI="https://tarikbozdemir2602.atlassian.net";
    }
    @DataProvider(name="TicketFields")
    public Object[][] ticketData(){
        return new Object[][]{
            {"TB","RestAssured Ticket Create3","doc","paragraph","text","Rest Assured Test3","Bug"}

        };
    }
    @Test(dataProvider = "TicketFields")
    void createDefect(String key, String summary, String descType, String contentType,String conContentType, String conContentText, String issueType){
        Response res=given()
                .header("Content-Type","application/json")
                .header("Authorization","Basic dGFyaWtib3pkZW1pcjI2MDJAZ21haWwuY29tOkFUQVRUM3hGZkdGMGd5VUt" +
                        "FVEtva1hPM3VPOEVreFUxd2JzTU9wOUhuM3ZHQzhjVGxnZ3ZoRElyY3I4SXVDUzV3clZOQ0pOVXpHVldDNWtOaXdIeEladG5BLVpQUU8zTlZkQU1nY05tMXM2emNlOTF0Q" +
                        "2MwXzcwaFhtX29MU29jRVFBYXFoTHhlc1BYNTZhMmV0d3J0clM2SzlmUUdpc05VZlVQVWtVc0dPN1g0UHVZNTJmNFdQZz0xMjgxODA1Mg==")
                .body("{\n" +
                        "  \"fields\": {\n" +
                        "    \"project\": {\n" +
                        "      \"key\": \""+key+"\"\n" +
                        "    },\n" +
                        "    \"summary\": \""+summary+"\",\n" +
                        "    \"description\": {\n" +
                        "      \"type\": \""+descType+"\",\n" +
                        "      \"version\": 1,\n" +
                        "      \"content\": [\n" +
                        "        {\n" +
                        "          \"type\": \""+contentType+"\",\n" +
                        "          \"content\": [\n" +
                        "            {\n" +
                        "              \"type\": \""+conContentType+"\",\n" +
                        "              \"text\": \""+conContentText+"\"\n" +
                        "            }\n" +
                        "          ]\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    \"issuetype\": {\n" +
                        "      \"name\": \""+issueType+"\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n")
                .when().post("rest/api/3/issue");
                res.then()
                .statusCode(201);
        System.out.println("Response body is -> "+res.getBody().asPrettyString());
        JsonPath js=res.jsonPath();
        String id=js.getString("id");
        System.out.println("Ticket id is :"+id);
// We will send an attachment to created ticket
        Response res2=
                given()
                        .header("X-Atlassian-Token","no-check")
                        .header("Authorization","Basic dGFyaWtib3pkZW1pcjI2MDJAZ21haWwuY29tOkFUQVRUM3hGZkdGMGd5VUt" +
                                "FVEtva1hPM3VPOEVreFUxd2JzTU9wOUhuM3ZHQzhjVGxnZ3ZoRElyY3I4SXVDUzV3clZOQ0pOVXpHVldDNWtOaXdIeEladG5BLVpQUU8zTlZkQU1nY05tMXM2emNlOTF0Q" +
                                "2MwXzcwaFhtX29MU29jRVFBYXFoTHhlc1BYNTZhMmV0d3J0clM2SzlmUUdpc05VZlVQVWtVc0dPN1g0UHVZNTJmNFdQZz0xMjgxODA1Mg==")
                        .pathParam("key",id)
                        .multiPart("file",new File("C:/Users/tbozdemir/Downloads/2025-06-02 08_48_40-New Request - My Workspace.png"))
                        .when().post("/rest/api/3/issue/{key}/attachments");
                        res2.then().log().all().statusCode(200);
    }
}
