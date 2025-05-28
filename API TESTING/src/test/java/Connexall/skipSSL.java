package Connexall;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class skipSSL {
    public void skipSSLCertValidation() {
        // Relax SSL certificate validation globally before tests run
        RestAssured.useRelaxedHTTPSValidation();
    }
}
