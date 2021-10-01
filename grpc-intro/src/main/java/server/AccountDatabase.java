package server;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountDatabase {

    public static final Map<Integer,Integer> MAP = IntStream.rangeClosed(1,10)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), v -> v *100));

    public static final int getBalance(int accountId){
        return MAP.get(accountId);
    }

    public static int addBalance(int accountId, int ammount) {
        return MAP.computeIfPresent(accountId,(v,k) -> v+ ammount);
    }

    public static int deductBalance(int accountId, int ammount) {
        return MAP.computeIfPresent(accountId,(v,k) -> v - ammount);
    }
}
