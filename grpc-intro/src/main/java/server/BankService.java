package server;

import com.models.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {

        int account  = request.getAccountNumber();
        Balance balance = Balance.newBuilder()
                .setAmount(AccountDatabase.getBalance(account)).build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();

    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        int account_number = request.getAccountNumber();
        int amount = request.getAmount();
        int balance = AccountDatabase.getBalance(account_number);

        if(balance < amount) {
            Status status = Status.FAILED_PRECONDITION.withDescription("No enough money, You have only " + balance);
            responseObserver.onError(status.asRuntimeException());
            return;
        }

        for (int i = 0; i < (amount/10); i++){
            Money money = Money.newBuilder().setValue(10).build();
            responseObserver.onNext(money);
            AccountDatabase.deductBalance(account_number,10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
        return super.cashDeposit(responseObserver);
    }
}
