import com.google.common.util.concurrent.Uninterruptibles;
import com.models.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.BankService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {

    private BankServiceGrpc.BankServiceBlockingStub blockingStub;
    private BankServiceGrpc.BankServiceStub stub;

    @BeforeAll
    public void setup(){
        ManagedChannel managedChannel =  ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        this.stub = BankServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void balanceTest() {
        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder().setAccountNumber(5).build();
        Balance balance = this.blockingStub.getBalance(balanceCheckRequest);
        System.out.println("Received " + balance);
    }

    @Test
    public void withdrawTest() {
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder().setAccountNumber(7).setAmount(40).build();
        this.blockingStub.withdraw(withdrawRequest)
                .forEachRemaining(money -> System.out.println("Money : " + money.getValue()));
    }

    @Test
    public void withdrawAsyncTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder().setAccountNumber(10).setAmount(50).build();
        this.stub.withdraw(withdrawRequest,new MoneyStreamingResponse(latch));
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        latch.await();
    }

    @Test
    public void cashStreamingRequest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<DepositRequest> depositRequestStreamObserver = this.stub.cashDeposit(new BalanceStreamObserver(latch));

        for (int i = 0 ; i < 10; i++){
            DepositRequest depositRequest= DepositRequest.newBuilder().setAmount(13).setAccountNumber(8).build();
            depositRequestStreamObserver.onNext(depositRequest);
        }

        depositRequestStreamObserver.onCompleted();
        latch.await();
    }

}
