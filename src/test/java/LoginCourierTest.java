import Model.CredentialCourierForCreate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginCourierTest {
    String loginCourier;
    String passwordCourier;

    StepsForPost stepsForCreateCourier = new StepsForPost();

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
    public void loginCourierAndCheckSuccessResponse() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        CredentialCourierForCreate credentialCourierForLogin = new CredentialCourierForCreate(loginCourier, passwordCourier);
        Response response =
                stepsForCreateCourier.doPostRequest("/api/v1/courier/login", credentialCourierForLogin);
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }
    @Test
    public void loginCourierWithoutFieldAndCheckNegativeResponse() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        CredentialCourierForCreate credentialCourierForLogin = new CredentialCourierForCreate("", passwordCourier);
        Response response =
                stepsForCreateCourier.doPostRequest("/api/v1/courier/login", credentialCourierForLogin);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }
    @Test
    public void uncorrectLoginCourierAndCheckNegativeResponse() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        CredentialCourierForCreate credentialCourierForLogin = new CredentialCourierForCreate(loginCourier + new Random().nextInt(10), passwordCourier);
        Response response =
                stepsForCreateCourier.doPostRequest("/api/v1/courier/login", credentialCourierForLogin);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

}
