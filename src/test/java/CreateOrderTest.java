import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    DataOrder dataOrder;
    int responseCode;
    int track;


    public CreateOrderTest(DataOrder dataOrder, int responseCode) {
        this.dataOrder = dataOrder;
        this.responseCode = responseCode;
    }

    @Parameterized.Parameters
    public static Object[] dataForTest() {
        Faker faker = new Faker();
        return new Object[][] {
                {new DataOrder(faker.name().firstName(), faker.name().lastName(), faker.address().streetAddress(), faker.number().numberBetween(1,10),
                        faker.name().firstName(), faker.number().numberBetween(1,10), faker.animal().name(), faker.book().title(), Arrays.asList("BLACK")), 201},
                {new DataOrder(faker.name().firstName(), faker.name().lastName(), faker.address().streetAddress(), faker.number().numberBetween(1,10),
                        faker.name().firstName(), faker.number().numberBetween(1,10), faker.animal().name(), faker.book().title(), Arrays.asList("BLACK", "GREY")), 201},
                {new DataOrder(faker.name().firstName(), faker.name().lastName(), faker.address().streetAddress(), faker.number().numberBetween(1,10),
                        faker.name().firstName(), faker.number().numberBetween(1,10), faker.animal().name(), faker.book().title(), Arrays.asList("")), 201},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @After
    public void orderDelete() {
        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.deleteOrder(track);
    }

    @Test
    public void createOrderAndCheckSuccessResponsePositive() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(dataOrder)
                        .when()
                        .post("/api/v1/orders");
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(responseCode);
        TrackOrder trackOrder = response.body().as(TrackOrder.class);
        track = trackOrder.getTrack();
    }
}
