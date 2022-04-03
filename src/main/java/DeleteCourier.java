import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteCourier {
    public void courierDelete(int id) {
        IdCourierDelete idCourierDelete = new IdCourierDelete(id);
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(idCourierDelete)
                .when()
                .delete("https://qa-scooter.praktikum-services.ru/api/v1/courier/" + id);

    }

}
