package server;

import com.models.Balance;
import com.models.BalanceCheckRequest;
import com.models.BankServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
//        responseObserver.onNext();
//        responseObserver.onError();
        int account  = request.getAccountNumber();
        Balance balance = Balance.newBuilder()
                .setAmount(AccountDatabase.getBalance(account)).build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }
}
