import client.StepsForGet;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
public class GetListOrdersTest {

    @Test
    @Description("В тело ответа возвращается список заказов")
    @DisplayName("В тело ответа возвращается список заказов")
    public void getListOrder() {
        StepsForGet.doGetRequestForGetListOrder()
        .then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}
