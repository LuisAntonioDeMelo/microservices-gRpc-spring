import com.models.Money;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class MoneyStreamingResponse implements StreamObserver<Money> {

    private CountDownLatch latch;

    MoneyStreamingResponse(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void onNext(Money money) {
        System.out.println("Received "+ money.getValue());
        latch.countDown();
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Done !!");
        latch.countDown();
    }
}
