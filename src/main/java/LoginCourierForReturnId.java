import Model.CredentialCourierForCreate;

import static io.restassured.RestAssured.given;

public class LoginCourierForReturnId {
    public int loginCourierAndReturnId(String login, String password) {
        CredentialCourierForCreate credentialCourierForLogin = new CredentialCourierForCreate(login, password);
        IdCourier idCourier =  given()
                .header("Content-type", "application/json")
                .and()
                .body(credentialCourierForLogin)
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login")
                .body().as(IdCourier.class);

        return idCourier.getId();
    }
}
