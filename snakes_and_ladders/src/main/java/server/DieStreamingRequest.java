package server;

import com.models.Die;
import com.models.GameState;
import com.models.Player;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ThreadLocalRandom;

public class DieStreamingRequest implements StreamObserver<Die> {

    private Player client;
    private Player server;
    private StreamObserver<GameState> gameStateStreamObserver;

    public DieStreamingRequest(Player client, Player server, StreamObserver<GameState> gameStateStreamObserver) {
        this.client = client;
        this.server = server;
        this.gameStateStreamObserver = gameStateStreamObserver;
    }

    @Override
    public void onNext(Die die) {
        this.client = getPlayerNewPosition(this.client, die.getValue());
        if(client.getPosition() != 100 ){
            this.server = this.getPlayerNewPosition(
                    this.server,
                    ThreadLocalRandom.current().nextInt(1,7)
            );
        }
        this.gameStateStreamObserver.onNext(this.getGameState());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }

    private GameState getGameState(){
        return GameState.newBuilder()
                .addPlayer(client)
                .addPlayer(server)
                .build();
    }

    private Player getPlayerNewPosition(Player player, int dieValue){
        int position = player.getPosition() + dieValue;
        if(position <= 100 ){
            player.toBuilder().setPosition(position).build();
        }
        return player;
    }
}
