package NewProjects.EComm;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    String JWTtoken;
    String productId;
    String userId;
    static{
        RestAssured.baseURI="https://rahulshettyacademy.com";
    }
    @Test()
    public void loginSite(){
        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        LoginRequest loginCredentials=new LoginRequest();
        String email= "tarikbozdemir2602@gmail.com";
        String password="Fener1907";
        loginCredentials.setUserEmail(email);
        loginCredentials.setPassword(password);

        RequestSpecification reqSpec=
                given()
                        //.log().all()
                        .spec(req)
                        .body(loginCredentials);

       /* Response res=reqSpec.when().post("/api/ecom/auth/login");
        System.out.println(res.getBody().asPrettyString()); */
        LoginResponse loginResponse=reqSpec.when().post("/api/ecom/auth/login")
        .then().extract().response().as(LoginResponse.class);
        System.out.println("Login token : "+loginResponse.getToken());
        JWTtoken=loginResponse.getToken();
        userId=loginResponse.getUserId();
    }
    @Test(dependsOnMethods = "loginSite")
    public void createProduct(){
        System.out.println("JWT Token :"+JWTtoken);
        RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",JWTtoken).build();
        RequestSpecification req2=given()
                .spec(reqSpec)
                .param("productName", "TarikShirt")
                .param("productAddedBy", userId)
                .param("productCategory", "fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Zara")
                .param("productFor","men")
                .multiPart("productImage",new File("C:\\Users\\tbozdemir\\Downloads\\shirt_resized.jpg"));
        Response res= req2.when().post("/api/ecom/product/add-product");
        System.out.println("CreateProduct response : "+res.getBody().asPrettyString());
        JsonPath js=res.jsonPath();
        productId=js.getString("productId");
        System.out.println("Product ID is : "+productId);
    }
    @Test(dependsOnMethods = "createProduct")
    public void createOrder(){
        Orders orders=new Orders();
        OrderDetails orderDetail=new OrderDetails();
        orderDetail.setCountry("Canada");
        orderDetail.setProductOrderedId(productId);
        List<OrderDetails> detailList=new ArrayList<OrderDetails>();
        detailList.add(orderDetail);
        orders.setOrders(detailList);
        System.out.println("JWT token inside the create order "+JWTtoken);

        RequestSpecification reqSpec=new RequestSpecBuilder().addHeader("authorization",JWTtoken)
                .setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com").build();
        RequestSpecification req3=given()
                .spec(reqSpec)
                .body(orders);
        Response res=req3.when().post("/api/ecom/order/create-order");
        System.out.println("Create order response = "+res.getBody().asPrettyString());
    }
    @Test(dependsOnMethods = "createOrder")
    public void deleteProduct(){
        Response res=
                given()
                        .header("authorization",JWTtoken)
                        .contentType("application/json")
                        .pathParam("productId",productId)
                        .when().delete("/api/ecom/product/delete-product/{productId}");
        System.out.println("Delete product response : "+res.getBody().asPrettyString());
    }

}
