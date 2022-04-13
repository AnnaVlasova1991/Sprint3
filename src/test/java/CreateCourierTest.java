import client.CourierSteps;
import client.StepsForDelete;
import io.qameta.allure.junit4.DisplayName;
import model.CredentialCourier;
import client.StepsForPost;
import com.github.javafaker.Faker;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Test;


import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

public class CreateCourierTest {

    String loginCourier = Faker.instance().name().username();
    String passwordCourier= Faker.instance().internet().password();

    @After
    public void deleteData() {
        int id = StepsForPost.doLoginCourierAndGetId(loginCourier, passwordCourier);
        StepsForDelete.doCourierDeleteRequest("/api/v1/courier/", id);
    }

    @Test
    @DisplayName("Курьера можно создать. Запрос возвращает правильный код ответа. Успешный запрос возвращает ok:true")
    @Description("Курьера можно создать. Запрос возвращает правильный код ответа. Успешный запрос возвращает ok:true")
    public void createNewCourierAndCheckResponse() {
        StepsForPost.doPostRequestForCreateCourier(new CredentialCourier(loginCourier, passwordCourier, Faker.instance().name().firstName()))
            .then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Description("Нельзя создать двух одинаковых курьеров")
    public void createDoubleCourierAndCheckNegativeResponse() {
        ArrayList<String> logoPass = CourierSteps.getCreatedCourier();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        StepsForPost.doPostRequestForCreateCourier(new CredentialCourier(loginCourier, passwordCourier, ""))
            .then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    @DisplayName("Если одного из полей нет, запрос возвращает ошибку")
    @Description("Если одного из полей нет, запрос возвращает ошибку")
    public void createCourierWithoutFieldAndCheckNegativeResponse() {
            StepsForPost.doPostRequestForCreateCourier(new CredentialCourier("", "password", "danzo"))
                .then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                    .and()
                    .statusCode(400);
    }

    @Test
    @DisplayName("Если создать пользователя с логином, который уже есть, возвращается ошибка")
    @Description("Если создать пользователя с логином, который уже есть, возвращается ошибка")
    public void createCourierLoginExistAndCheckNegativeResponse() {
        ArrayList<String> logoPass = CourierSteps.getCreatedCourier();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        StepsForPost.doPostRequestForCreateCourier(new CredentialCourier(loginCourier, Faker.instance().internet().password(), Faker.instance().name().firstName()))
            .then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

}
