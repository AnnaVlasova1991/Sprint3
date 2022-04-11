import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class DeleteOrder {
    public void deleteOrder(int track) {
        TrackOrder trackOrderForDelete = new TrackOrder(track);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(trackOrderForDelete)
                        .when()
                        .delete("qa-scooter.praktikum-services.ru/api/v1/orders/cancel");
    }
}
