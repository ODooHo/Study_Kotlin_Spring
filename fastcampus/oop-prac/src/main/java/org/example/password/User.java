package org.example.password;

public class User {
    private String password;

    public void initPassword(PasswordGeneratePolicy passwordGeneratePolicy){
        String randomPassword = passwordGeneratePolicy.generatePassword();
        /**
         * 비밀번호는 8자 이상 12자 이하여야한다.
         */

        if(randomPassword.length() >= 8 && randomPassword.length() <= 12){
            this.password = randomPassword;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
