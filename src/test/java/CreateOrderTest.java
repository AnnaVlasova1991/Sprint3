import client.StepsForDelete;
import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import model.DataOrder;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.Matchers.notNullValue;

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
                        faker.phoneNumber().phoneNumber(), faker.number().numberBetween(1,10), "2020-06-06", faker.book().title(), Arrays.asList("BLACK")), 201},
                {new DataOrder(faker.name().firstName(), faker.name().lastName(), faker.address().streetAddress(), faker.number().numberBetween(1,10),
                        faker.name().firstName(), faker.number().numberBetween(1,10), "2020-06-07", faker.book().title(), Arrays.asList("BLACK", "GREY")), 201},
                {new DataOrder(faker.name().firstName(), faker.name().lastName(), faker.address().streetAddress(), faker.number().numberBetween(1,10),
                        faker.name().firstName(), faker.number().numberBetween(1,10), "2020-06-08", faker.book().title(), Arrays.asList("")), 201},
        };
    }

    @After
    public void orderDelete() {
        StepsForDelete.doOrderDeleteRequest(track);
    }

    @Test
    @Description("Проверка на:  можно указать один из цветов — BLACK или GREY; можно указать оба цвета; можно совсем не указывать цвет; тело ответа содержит track")
    @DisplayName("Проверка на:  можно указать один из цветов — BLACK или GREY; можно указать оба цвета; можно совсем не указывать цвет; тело ответа содержит track")
    public void createOrderAndCheckSuccessResponsePositive() {
        Response response = StepsForPost.doPostRequestForCreateOrder(dataOrder);
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(responseCode);

        track = response.body().as(DataOrder.class).getTrack();
    }

}
