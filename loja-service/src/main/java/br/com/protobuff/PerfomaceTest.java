package br.com.protobuff;

import br.com.json.JPerson;
import br.com.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

public class PerfomaceTest {

    public static void main(String[] args) {
        //json
        JPerson person = new JPerson();
        person.setAge(10);
        person.setName("sam");
        ObjectMapper mapper = new ObjectMapper();

        Runnable runnable1 = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(person);
                JPerson person1 = mapper.readValue(bytes,JPerson.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Person p2 = Person.newBuilder().setAge(10).setName("sam").build();
        //protobuff
        Runnable runnable2 = () -> {
            try {
                byte[] bytes = p2.toByteArray();
                Person pb = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        for(int i = 0; i < 5; i++) {
            runPerformaceTest(runnable1, ".json");
            runPerformaceTest(runnable2, ".protobuf");
        }
    }

    private static void runPerformaceTest(Runnable runnable, String method){
        long time = System.currentTimeMillis();
        for (int i =0 ; i < 5_000_000 ; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();
        System.out.println(method + " : " + (time2 - time) + " ms ");
    }
}
