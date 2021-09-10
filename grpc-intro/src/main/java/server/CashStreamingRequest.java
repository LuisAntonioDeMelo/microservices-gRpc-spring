package server;

import com.models.Balance;
import com.models.DepositRequest;
import io.grpc.stub.StreamObserver;

public class CashStreamingRequest implements StreamObserver<DepositRequest> {

    private StreamObserver<Balance> balanceStreamObserver;
    private int accountBalance;

    @Override
    public void onNext(DepositRequest depositRequest) {
        int accountNumber = depositRequest.getAccountNumber();
        int amount =  depositRequest.getAmount();
        this.accountBalance = AccountDatabase.getBalance(accountNumber);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        Balance balance = Balance.newBuilder().setAmount(accountBalance).build();
        this.balanceStreamObserver.onNext(balance);
        this.balanceStreamObserver.onCompleted();
    }
}

