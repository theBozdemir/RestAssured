package API_Recap.ECommerceRecap;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Ecommerce {
    String token;
    String userId;
    String productId;

    @DataProvider(name = "login")
    public Object[][] loginCredentals() {
        return new Object[][]{{"tbozdemir@gmail.com", "Sevdis2602"}};

    }

    @Test(dataProvider = "login")
    public void login(String username, String password) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        LoginResponse lp = given()
                .contentType("application/json")
                .body("{\"userEmail\":\"" + username + "\",\"userPassword\":\"" + password + "\"}")
                .when().post("/api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);

        token = lp.getToken();
        userId = lp.getUserId();
        System.out.println("Token = " + token);
        System.out.println("UserID = " + userId);


    }

    @Test(dependsOnMethods = "login")
    public void addProduct() {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token).build();
        RequestSpecification reqSpes = given()
                .spec(req)
                .param("productName", "Adidas Tshirt")
                .param("productAddedBy", userId)
                .param("productCategory", "fashion")
                .param("productSubCategory", "shirts")
                .param("productPrice", "11500")
                .param("productDescription", "Adidas Originals")
                .param("productFor", "women")
                .multiPart("productImage", new File("C://Users//tbozdemir//Downloads//shirt.png"));

        String addProductRes = reqSpes.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(addProductRes);
        productId = js.getString("productId");
        System.out.println("Product ID = " + productId);
    }

    @Test(dependsOnMethods = "addProduct")
    public void CreateOrder() {
        System.out.println("Product ID within create order = " + productId);
        OrderReq orderReq=new OrderReq();
        orderReq.setCountry("Turkey");
        orderReq.setproductOrderedId(productId);
        List<OrderReq> ordersDetailList=new ArrayList<>();
        ordersDetailList.add(orderReq);
        CreateOrder createOrderPojo=new CreateOrder();
        createOrderPojo.setOrders(ordersDetailList);
        RequestSpecification createOrder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token).setContentType(ContentType.JSON).build();
        RequestSpecification req2= given().spec(createOrder).body(createOrderPojo);

        String response=req2.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();



    }
    @Test(dependsOnMethods = "addProduct")
    public void deleteProduct(){
        RequestSpecification deleteReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",token)
                .build();
        //RelaxedHttpsValidation helps user to bypass SSL certification
        RequestSpecification deleteProduct=given().relaxedHTTPSValidation().spec(deleteReq).pathParam("productId",productId);
        String deleteResponse=deleteProduct.when().delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();

        JsonPath js=new JsonPath(deleteResponse);
        String message=js.getString("message");
        String expectedMessage="Product Deleted Successfully";
        Assert.assertEquals(message,expectedMessage);

    }

}
