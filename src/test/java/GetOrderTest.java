import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetOrderTest {

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
    public void getOrderAndCheckResponse() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        int idCourier = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        CreateOrder createOrder = new CreateOrder();
        int trackOrder = createOrder.createOrderAndReturnTrack();
        GetOrderId getOrderId = new GetOrderId();
        int idOrder = getOrderId.getOrderId(trackOrder);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .put("/api/v1/orders/accept/" + idOrder + "?courierId=" + idCourier);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(200);
    }
    @Test
    public void getOrderWithoutIdCourier() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        int idCourier = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        CreateOrder createOrder = new CreateOrder();
        int trackOrder = createOrder.createOrderAndReturnTrack();
        GetOrderId getOrderId = new GetOrderId();
        int idOrder = getOrderId.getOrderId(trackOrder);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .put("/api/v1/orders/accept/" + idOrder + "?courierId=");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для поиска"))
                .and()
                .statusCode(400);
    }
    @Test
    public void getOrderUncorrectIdCourier() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        int idCourier = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        CreateOrder createOrder = new CreateOrder();
        int trackOrder = createOrder.createOrderAndReturnTrack();
        GetOrderId getOrderId = new GetOrderId();
        int idOrder = getOrderId.getOrderId(trackOrder);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .put("/api/v1/orders/accept/" + idOrder + "?courierId=" + idCourier + new Random().nextInt(10));
        response.then().assertThat().body("message", equalTo("Курьера с таким id не существует"))
                .and()
                .statusCode(404);
    }
    @Test
    public void getOrderWithoutIdOrder() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        int idCourier = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        CreateOrder createOrder = new CreateOrder();
        int trackOrder = createOrder.createOrderAndReturnTrack();
        GetOrderId getOrderId = new GetOrderId();
        int idOrder = getOrderId.getOrderId(trackOrder);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .put("/api/v1/orders/accept/" + "courierId=" + idCourier);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для поиска"))
                .and()
                .statusCode(400);
        }
    @Test
    public void getOrderUncorrectIdOrder() {
        scooterRegisterCourier scooterRegisterCourier = new scooterRegisterCourier();
        ArrayList<String> logoPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        LoginCourierForReturnId loginCourierForReturnId = new LoginCourierForReturnId();
        int idCourier = loginCourierForReturnId.loginCourierAndReturnId(loginCourier, passwordCourier);
        CreateOrder createOrder = new CreateOrder();
        int trackOrder = createOrder.createOrderAndReturnTrack();
        GetOrderId getOrderId = new GetOrderId();
        int idOrder = getOrderId.getOrderId(trackOrder);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .put("/api/v1/orders/accept/" + idOrder + new Random().nextInt(10) + "?courierId=" + idCourier);
        response.then().assertThat().body("message", equalTo("Заказа с таким id не существует"))
                .and()
                .statusCode(404);
        }


}
