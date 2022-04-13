package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialCourier {
    private String login;
    private String password;
    private String firstName;
    private boolean ok;
    private int id;
    private int code;
    private String message;

    public CredentialCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;

    }
    public CredentialCourier(String login, String password) {
        this.login = login;
        this.password = password;
        this.firstName = null;
    }

    public CredentialCourier(int id, int code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    public CredentialCourier(int id) {
        this.id = id;
    }

    public CredentialCourier(boolean ok) {
        this.login = null;
        this.password = null;
        this.firstName = null;
        this.ok = ok;
    }

    public CredentialCourier() {
    }
}
