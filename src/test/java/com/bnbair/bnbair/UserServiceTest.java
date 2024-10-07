package com.bnbair.bnbair;

import com.bnbair.bnbair.domain.User;
import com.bnbair.bnbair.exception.UserNotFoundException;
import com.bnbair.bnbair.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("John", createdUser.getFirstName());
        assertEquals("Doe", createdUser.getLastName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
    }

    @Test
    void testFindUserById() throws UserNotFoundException {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.com");

        User createdUser = userService.createUser(user);
        User foundUser = userService.getUserById(createdUser.getId());

        assertNotNull(foundUser);
        assertEquals("Jane", foundUser.getFirstName());
        assertEquals("Doe", foundUser.getLastName());
        assertEquals("jane.doe@example.com", foundUser.getEmail());
    }

    @Test
    void testUpdateUser() throws UserNotFoundException {
        User user = new User();
        user.setFirstName("Alice");
        user.setLastName("Smith");
        user.setEmail("alice.smith@example.com");

        User createdUser = userService.createUser(user);

        User updatedUser = new User();
        updatedUser.setFirstName("AliceUpdated");
        updatedUser.setLastName("SmithUpdated");
        updatedUser.setEmail("alice.updated@example.com");

        User result = userService.updateUser(createdUser.getId(), updatedUser);

        assertNotNull(result);
        assertEquals("AliceUpdated", result.getFirstName());
        assertEquals("SmithUpdated", result.getLastName());
        assertEquals("alice.updated@example.com", result.getEmail());
    }

    @Test
    void testDeleteUser() throws UserNotFoundException {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Brown");
        user.setEmail("bob.brown@example.com");

        User createdUser = userService.createUser(user);
        Long userId = createdUser.getId();

        userService.deleteUser(userId);

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setFirstName("User1");
        user1.setLastName("Test1");
        user1.setEmail("user1.test@example.com");

        User user2 = new User();
        user2.setFirstName("User2");
        user2.setLastName("Test2");
        user2.setEmail("user2.test@example.com");

        userService.createUser(user1);
        userService.createUser(user2);

        List<User> users = userService.getAllUsers();
        assertTrue(users.size() >= 2);
    }

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setFirstName("Charlie");
        user.setLastName("Delta");
        user.setEmail("charlie.delta@example.com");

        userService.createUser(user);
        List<User> usersByEmail = userService.getUsersByEmail("charlie.delta@example.com");

        assertNotNull(usersByEmail);
        assertFalse(usersByEmail.isEmpty());
        assertEquals("Charlie", usersByEmail.getFirst().getFirstName());
        assertEquals("Delta", usersByEmail.getFirst().getLastName());
    }

    @Test
    void testFindByFirstAndLastName() {
        User user = new User();
        user.setFirstName("Eve");
        user.setLastName("Echo");
        user.setEmail("eve.echo@example.com");

        userService.createUser(user);
        List<User> users = userService.getUsersByFirstAndLastName("Eve", "Echo");

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals("Eve", users.getFirst().getFirstName());
        assertEquals("Echo", users.getFirst().getLastName());
    }

    @Test
    void testUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(999L));
    }
}
