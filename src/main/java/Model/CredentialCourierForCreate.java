package Model;

public class CredentialCourierForCreate {
    private String login;
    private String password;
    private String firstName;
    private boolean ok;

    public CredentialCourierForCreate(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;

    }
    public CredentialCourierForCreate(String login, String password) {
        this.login = login;
        this.password = password;
        this.firstName = null;
    }

    public CredentialCourierForCreate(boolean ok) {
        this.login = null;
        this.password = null;
        this.firstName = null;
        this.ok = ok;
    }

    public CredentialCourierForCreate() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
