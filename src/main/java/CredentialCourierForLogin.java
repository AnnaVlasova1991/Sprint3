public class CredentialCourierForLogin {
    private String login;
    private String password;

    public CredentialCourierForLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public CredentialCourierForLogin() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
