package server;

import com.models.Account;
import com.models.TransferRequest;
import com.models.TransferResponse;
import com.models.TransferStatus;
import io.grpc.stub.StreamObserver;

public class TransferStreamingRequest implements StreamObserver<TransferRequest> {

    private StreamObserver<TransferResponse> transferResponseStreamObserver;

    public TransferStreamingRequest(StreamObserver<TransferResponse> transferResponseStreamObserver) {
        this.transferResponseStreamObserver = transferResponseStreamObserver;
    }

    @Override
    public void onNext(TransferRequest transferRequest) {
        int fromAccount = transferRequest.getFromAccount();
        int toAccount = transferRequest.getToAccount();
        int amount = transferRequest.getAmount();
        int balance = AccountDatabase.getBalance(fromAccount);
        TransferStatus status = TransferStatus.FAILED;

        if(balance >= amount && fromAccount != toAccount){
            AccountDatabase.deductBalance(fromAccount,amount);
            AccountDatabase.addBalance(toAccount,amount);
            status = TransferStatus.SUCCESS;
        }
        Account fromAccountInfo = Account.newBuilder()
                .setAccountNumber(fromAccount)
                .setAmount(AccountDatabase.deductBalance(fromAccount,amount))
                .build();

        Account toAccountInfo = Account.newBuilder()
                .setAccountNumber(toAccount)
                .setAmount(AccountDatabase.addBalance(toAccount,amount))
                .build();

        TransferResponse transferResponse = TransferResponse.newBuilder()
                .addAccount(fromAccountInfo)
                .addAccount(toAccountInfo)
                .setStatus(status)
                .build();

        this.transferResponseStreamObserver.onNext(transferResponse);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        this.transferResponseStreamObserver.onCompleted();
    }
}
