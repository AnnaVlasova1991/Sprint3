import java.util.Random;

public class UserNameGenerator {
   private String [] logins = new String[] {"koko" + new Random().nextInt(100), "murmur" + new Random().nextInt(100), "mur" + new Random().nextInt(100),
           "petya" + new Random().nextInt(100), "sonya" + new Random().nextInt(100)};
   private String [] passwords = new String[] {"password1", "password2", "password3", "password4"};
   private String [] firstNames = new String[] {"saske", "naruto", "sakura", "hinata"};

    public String getLogin() {
        int randomLogin = new Random().nextInt(logins.length);
        return logins[randomLogin];
    }

    public String getPassword() {
        int randomPassword = new Random().nextInt(passwords.length);
        return passwords[randomPassword];
    }

    public String getFirstName() {
        int randomFirstName = new Random().nextInt(firstNames.length);
        return firstNames[randomFirstName];
    }
}
