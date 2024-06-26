package org.example.password;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {
    @DisplayName("패스워드를 초기화한다.")
    @Test
    void passwordTest() {

        //given
        User user = new User();
        //when
        user.initPassword(new CorrectPasswordGenerator());
        //then
        assertThat(user.getPassword()).isNotNull();
    }
}