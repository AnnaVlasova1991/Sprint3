import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateCourierTest {

    String loginCourier;
    String passwordCourier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @After
    public void deleteData() {
        DeleteCourier deleteCourier = new DeleteCourier();
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        int id = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        deleteCourier.courierDelete(id);
    }

    @Test
    public void createNewCourierAndCheckResponse() {
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        loginCourier = userNameGenerator.getLogin();
        passwordCourier = userNameGenerator.getPassword();
        CredentialCourierForCreate credentialCourier = new CredentialCourierForCreate(loginCourier,
                passwordCourier,
                userNameGenerator.getFirstName());
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(credentialCourier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void createDoubleCourierAndCheckNegativeResponse() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        CredentialCourierForCreate credentialCourier = new CredentialCourierForCreate(loginCourier, passwordCourier, "");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(credentialCourier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }
    @Test
    public void createCourierWithoutFieldAndCheckNegativeResponse() {
        CredentialCourierForCreate credentialCourier = new CredentialCourierForCreate("", "password", "danzo");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(credentialCourier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }
    @Test
    public void createNewCourierAndCheckResponseCode() {
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        loginCourier = userNameGenerator.getLogin();
        passwordCourier = userNameGenerator.getPassword();
        CredentialCourierForCreate credentialCourier = new CredentialCourierForCreate(loginCourier, passwordCourier,
                userNameGenerator.getFirstName());
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(credentialCourier)
                        .when()
                        .post("/api/v1/courier");
        response.then()
                .statusCode(201);
    }
    @Test
    public void createCourierLoginExistAndCheckNegativeResponse() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        String login = logoPass.get(0);
        CredentialCourierForCreate credentialCourier = new CredentialCourierForCreate(login, "55555", "kuku");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(credentialCourier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

}
