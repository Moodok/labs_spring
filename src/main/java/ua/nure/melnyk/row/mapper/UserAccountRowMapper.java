package ua.nure.melnyk.row.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.entity.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAccountRowMapper implements RowMapper<UserAccount> {

    public static final String ID = "id";
    public static final String USER_ID = "userId";
    public static final String BALANCE = "balance";

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(rs.getLong(ID));
        userAccount.setUserId(rs.getLong(USER_ID));
        userAccount.setBalance(rs.getInt(BALANCE));
        return userAccount;
    }

}
