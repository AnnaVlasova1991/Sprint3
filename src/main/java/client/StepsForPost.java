package client;

import model.CredentialCourier;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.DataOrder;

import static io.restassured.RestAssured.given;

public class StepsForPost extends BaseHTTPClient {

    @Step(value = "Создаем Курьера. Делаем POST запрос по endpoint: \"/api/v1/courier\". Передаем login={credentialCourierForCreate.login}, password={credentialCourierForCreate.password}, fitstName")
    public static Response doPostRequestForCreateCourier(CredentialCourier credentialCourier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(credentialCourier)
                .when()
                .post(BASEURL + "/api/v1/courier");
    }

    @Step(value = "Логинимся курьером и получаем его ID. Делаем POST запрос по endpoint: \"/api/v1/courier/login\". Передаем login={login}, password={password}.")
    public static int doLoginCourierAndGetId(String login, String password){
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(new CredentialCourier(login, password))
                .when()
                .post(BASEURL + "/api/v1/courier/login").body().as(CredentialCourier.class).getId();
    }

    @Step(value = "Создаем заказ. Делаем POST запрос на endpoint: \"/api/v1/orders\".")
    public static Response doPostRequestForCreateOrder(DataOrder dataOrder){
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(dataOrder)
                .when()
                .post(BASEURL + "/api/v1/orders");
    }

}
