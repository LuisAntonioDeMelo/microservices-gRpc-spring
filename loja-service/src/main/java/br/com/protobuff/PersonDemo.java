package br.com.protobuff;

import br.com.models.*;
import br.com.models.common.Address;
import br.com.models.common.BodyType;
import br.com.models.common.Car;
import org.apache.tomcat.jni.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonDemo {

    public static void main(String[] args) throws IOException {
        //Person p = Person.newBuilder().setName("sam").setAge(20).build();
        Path path = Paths.get("sam.ser");
        //Files.write(path, p.toByteArray());

        byte[] bt = Files.readAllBytes(path);
        Person p =  Person.parseFrom(bt);

       // System.out.println(p);
        Car c = Car.newBuilder().setModel("Logan").setYear("2020").setPlac("PTL4221").setBodyType(BodyType.SEDAN).build();
        Car c2 = Car.newBuilder().setModel("Acoord").setYear("2020").setPlac("CKJ4891").build();
        Address address = Address.newBuilder().setCity("Gyn").setAvenue("fleminthon").setStreet("alpes").setPostbox("74310220").build();

        List<Car> carList = new ArrayList<>();
        carList.add(c);
        carList.add(c2);

        Person p2 = Person.newBuilder()
                .setAddress(address)
                .setAge(10)
                .setName("sam")
                .addAllCars(carList)
                .build();
        System.out.println(p2);

        Dealer dealer = Dealer.newBuilder()
                .putModel(2000,c)
                .putModel(2020,c2)
                .build();

        System.out.println(dealer.getModelOrThrow(2020).getBodyType());
    }
}
