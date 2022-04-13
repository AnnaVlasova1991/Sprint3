import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.ScooterRegisterCourier;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class DeleteCourierTest {
//    @Before
//    public void setUp() {
//        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
//    }
    @Test
    public void deleteCourierSuccessCheckBody() {
        ScooterRegisterCourier scooterRegisterCourier = new ScooterRegisterCourier();
        //DeleteCourier deleteCourier = new DeleteCourier();
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        String loginCourier = logoPass.get(0);
        String passwordCourier = logoPass.get(1);
        int id = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        IdCourier idCourierDelete = new IdCourier(id);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(idCourierDelete)
                        .when()
                        .delete("/api/v1/courier/" + id);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(200);
    }
    @Test
    public void deleteCourierWithoutIdCheckBody() {

        IdCourier idCourierDelete = new IdCourier();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(idCourierDelete)
                        .when()
                        .delete("/api/v1/courier/");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для удаления курьера"))
                .and()
                .statusCode(400);
    }
    @Test
    public void deleteCourierWithUncorrectIdCheckBody() {

        int id = new Random().nextInt(56473845);
        IdCourier idCourierDelete = new IdCourier(id);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(idCourierDelete)
                        .when()
                        .delete("/api/v1/courier/" + id);
        response.then().assertThat().body("message", equalTo("Курьера с таким id нет."))
                .and()
                .statusCode(404);
    }

}
