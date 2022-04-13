package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CredentialCourier;
import model.DataOrder;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class StepsForDelete extends BaseHTTPClient{

    @Step(value = "Удаление курьера. Запрос метод DELETE по endpoint={uri}{id}")
    public static void doCourierDeleteRequest(String uri, int id){
       given()
            .header("Content-type", "application/json")
            .and()
            .body(new CredentialCourier(id))
            .when()
            .delete(BASEURL + uri + id);
    }

    @Step(value = "Удаляем заказ с track={track}. Запрос метод DELETE по endpoint \"/api/v1/orders/cancel\"")
    public static void doOrderDeleteRequest(int track){
        given()
            .header("Content-type", "application/json")
            .and()
            .body(new DataOrder(track))
            .when()
            .delete(BASEURL + "/api/v1/orders/cancel");
    }
}

