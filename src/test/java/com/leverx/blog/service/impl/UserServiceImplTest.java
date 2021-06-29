package com.leverx.blog.service.impl;

import com.leverx.blog.configuration.AppConfig;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.model.entity.UserStatus;
import com.leverx.blog.repository.mysql.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AppConfig.class})
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findPresentUser() {

        User expected = new User();
        expected.setId(1);
        expected.setFirstName("Kirill");
        expected.setLastName("Lisitsa");
        expected.setPassword("sdfadsvffvdc");
        expected.setEmail("kirilllisitsa@gmail.com");
        expected.setRegistrationDate(LocalDate.now());
        expected.setUserStatus(UserStatus.ACTIVATED);

        User actual = userRepository.findUserByEmail("kirilllisitsa@gmail.com").get();

        Assert.assertEquals(expected, actual);
    }
}