package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.DataOrder;

import static io.restassured.RestAssured.given;

public class StepsForGet extends BaseHTTPClient{
    @Step(value = "Получение списка заказов. Делаем GET запрос на endpoint: \"/api/v1/orders\".")
    public static Response doGetRequestForGetListOrder(){
        return given()
                .header("Content-Type", "application/json")
                .get(BASEURL + "/api/v1/orders");
    }
}
