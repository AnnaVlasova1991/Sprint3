package client;

import io.qameta.allure.Step;

import java.util.ArrayList;
import model.ScooterRegisterCourier;

public class CourierSteps {

    @Step(value = "Создаем нового курьера")
    public static ArrayList<String> getCreatedCourier(){
        return new ScooterRegisterCourier().registerNewCourierAndReturnLoginPassword();
    }

}
