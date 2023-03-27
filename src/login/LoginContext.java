package login;

import java.sql.SQLException;

public class LoginContext {
    public static void execute(Login login) throws SQLException {
        login.login();
    }
}
