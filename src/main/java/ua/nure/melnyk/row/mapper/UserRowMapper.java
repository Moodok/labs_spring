package ua.nure.melnyk.row.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phoneNumber";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setEmail(resultSet.getString(EMAIL));
        user.setName(resultSet.getString(NAME));
        user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
        return user;
    }

}
