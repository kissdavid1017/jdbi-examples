package user;

import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.util.Locale;


import static org.apache.commons.codec.digest.DigestUtils.md5Hex;



public class FakerUser {
    private final Faker faker;

    public FakerUser(Locale locale)
    {
        this.faker=new Faker(locale);
    }

    public User getFakerUser(){
        User user=User.builder()
                .username(faker.name().username())
                .password(md5Hex(faker.internet().password()))
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option(User.Gender.FEMALE,User.Gender.MALE))
                .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .enabled(faker.bool().bool())
                .build();
        return user;
    }



}
