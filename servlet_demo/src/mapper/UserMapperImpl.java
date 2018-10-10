package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entity.User;

/**
 * 此接口RowMapper<T> 是Spring提供的
 * @author zte
 *
 */
public class UserMapperImpl implements RowMapper<User> 
{
    /**
     * 行映射   把DB中t_user表中的数据取出来  ----> pojo/JavaBean/实体Bean  User
     */
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		User user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
		
		return user;
	}

}
