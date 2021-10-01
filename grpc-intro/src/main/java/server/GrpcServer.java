package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .addService(new TransferService())
                .build();
        server.start();
        System.out.println("###########################################################################\n" +
                            "      ##############   gRpc Server : port 6565       ################");
        System.out.println("Running ::::::::::::::::::: ");
        server.awaitTermination();
    }
}
