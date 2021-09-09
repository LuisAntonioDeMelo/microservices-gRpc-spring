package br.com.protobuff;

import br.com.models.Credentials;
import br.com.models.EmailCredentials;
import br.com.models.PhoneOTP;

public class CredentialsDemo {

    public static void main(String[] args) {
        PhoneOTP phone = PhoneOTP.newBuilder()
                .setCode(55)
                .setNumber(629999999)
                .build();

        EmailCredentials email = EmailCredentials.newBuilder()
                .setEmail("tyluis20@gmail.com")
                .setPassword("password")
                .build();

        Credentials credentials = Credentials.newBuilder()
                .setEmailMode(email)
                .setPhoneMode(phone)
                .build();

        switch (credentials.getModeCase()){
            case EMAILMODE:
                System.out.println(credentials);
                break;
            case PHONEMODE:
                System.out.println(phone);
                break;
        }


    }
}
