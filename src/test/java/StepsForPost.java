import Model.CredentialCourierForCreate;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepsForPost {

    public Response doPostRequest(String uri, CredentialCourierForCreate credentialCourierForCreate) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(credentialCourierForCreate)
                .when()
                .post(uri);
    }


}
