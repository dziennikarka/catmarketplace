package fi.haagahelia.catmarketplace;

import fi.haagahelia.catmarketplace.domain.User;
import fi.haagahelia.catmarketplace.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername(){
        User user  = userRepository.findByUsername("user");
        assertThat(user.getEmail().equals("user@gmail.com"));
    }

    @Test
    public void createNewUser(){
        User user = new User("catfan", "catfan@gmail.com", "$2a$10$l3ILFzZSIPThyAnAKmCQeuzHtPkuqYBk9xlsddcg2k0Coi0FEYaPa", "ADMIN");
        userRepository.save(user);
        assertThat(user.getUserId()).isNotNull();
        assertThat(user.getEmail().equals("catfan@gmail.com"));
    }

    @Test
    @Rollback(false)
    public void deleteUser(){
        User user = userRepository.findByUsername("user");
        userRepository.delete(user);
        Optional<User>deleteUser = userRepository.findById(user.getUserId());
        assertThat(deleteUser).isEmpty();
    }
}
