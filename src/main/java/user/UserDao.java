package user;




import ex10.YearArgumentFactory;
import ex10.YearColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterArgumentFactory(YearArgumentFactory.class)
@RegisterColumnMapper(YearColumnMapper.class)
@RegisterBeanMapper(User.class)
public interface UserDao {
    @SqlUpdate("""
            Create table user(
            id IDENTITY primary key,
            username varchar not null unique,
            password varchar,
            name varchar,
            email varchar,
            gender ENUM('FEMALE','MALE'),
            birthDate DATE,
            enabled BIT
            )
            """

    )
    void createTable();
    @SqlUpdate("INSERT INTO user(username,password,name,email,gender,birthDate,enabled) VALUES(:username,:password,:name,:email,:gender,:birthDate,:enabled)")
    @GetGeneratedKeys
    long insertUser(@BindBean User user);

    @SqlQuery("SELECT * from user where id=:id")
    Optional<User>getUser(@Bind("id") long id);

    @SqlQuery("SELECT * from user where name=:name" )
    Optional<User>getUser(@Bind("name") String name);

    @SqlUpdate("DELETE from user Where username=:us.username")
    void deleteUser(@BindBean("us") User user);
    @SqlQuery("SELECT * from user")
    List<User>usersList();

}
