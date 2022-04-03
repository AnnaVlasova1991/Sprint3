import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDataGenerator {
    private String firstName = RandomStringUtils.randomAlphabetic(10);
    private String lastName = RandomStringUtils.randomAlphabetic(10);
    private String address = RandomStringUtils.randomAlphabetic(10) + new Random().nextInt(100);
    private int metroStation = new Random().nextInt(100);
    private String phone = new Random().nextInt(100) + "";
    private int rentTime = new Random().nextInt(100);
    private String deliveryDate = new Random().nextInt(2022) + "-" + new Random().nextInt(12) + "-" + new Random().nextInt(30);
    private String comment = RandomStringUtils.randomAlphabetic(10);
    private List<String> color = new ArrayList<String>(){
        {
            add("BLACK");
            add("GREY");
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getColor() {
        return color;
    }
}
