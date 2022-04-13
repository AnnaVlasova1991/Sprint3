import io.restassured.response.Response;
import model.DataOrder;

import static io.restassured.RestAssured.given;
public class DeleteOrder {
    public void deleteOrder(int track) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(new DataOrder(track))
                        .when()
                        .delete("qa-scooter.praktikum-services.ru/api/v1/orders/cancel");
    }
}
