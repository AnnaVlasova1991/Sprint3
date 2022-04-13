package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataOrder {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    private int track;
    private int code;
    private String message;

    public DataOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public DataOrder(int track, int code, String message) {
        this.track = track;
        this.code = code;
        this.message = message;
    }

    public DataOrder(int track) {
        this.track = track;
        this.message = null;
    }

    public DataOrder() {
    }
}
