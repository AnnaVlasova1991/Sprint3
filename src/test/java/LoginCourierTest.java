import client.CourierSteps;
import client.StepsForDelete;
import client.StepsForPost;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import model.CredentialCourier;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.Matchers.*;

public class LoginCourierTest {

    String loginCourier;
    String passwordCourier;

    @After
    public void deleteData() {
        int id = StepsForPost.doLoginCourierAndGetId(loginCourier, passwordCourier);
        StepsForDelete.doCourierDeleteRequest("/api/v1/courier/", id);
    }

    @Test
    @DisplayName("Курьер может авторизоваться. Для авторизации нужно передать все обязательные поля. Успешный запрос возвращает id")
    @Description("Курьер может авторизоваться. Для авторизации нужно передать все обязательные поля. Успешный запрос возвращает id")
    public void loginCourierAndCheckSuccessResponse() {
        ArrayList<String> logoPass = CourierSteps.getCreatedCourier();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        StepsForPost.doLoginCourier(loginCourier, passwordCourier)
            .then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Для авторизации нужно передать все обязательные поля. Если какого-то поля нет, запрос возвращает ошибку.")
    @Description("Для авторизации нужно передать все обязательные поля. Если какого-то поля нет, запрос возвращает ошибку.")
    public void loginCourierWithoutFieldAndCheckNegativeResponse() {
        ArrayList<String> logoPass = CourierSteps.getCreatedCourier();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        StepsForPost.doLoginCourier("", passwordCourier)
            .then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Система вернёт ошибку, если неправильно указать логин или пароль. Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку.")
    @Description("Система вернёт ошибку, если неправильно указать логин или пароль. Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку.")
    public void uncorrectLoginCourierAndCheckNegativeResponse() {
        ArrayList<String> logoPass = CourierSteps.getCreatedCourier();
        loginCourier = logoPass.get(0);
        passwordCourier = logoPass.get(1);
        StepsForPost.doLoginCourier(loginCourier + new Random().nextInt(10), passwordCourier)
            .then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

}
