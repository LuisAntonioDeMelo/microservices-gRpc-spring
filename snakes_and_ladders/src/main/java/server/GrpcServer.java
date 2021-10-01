package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(6565)
                .addService(new GameService())
                .build();
        server.start();

        System.out.println("  ##############   gRpc Server : port 6565       ################");
        System.out.println(" h3hh131h3h123 h12h313 h3123h13 31h3h131 1h23h123 123h1231 h312h313h");
        System.out.println(" h3hh131h3h123 h12h313 h3123h13 31h3h131 1h23h123 123h1231 h312h313h");
        System.out.println(" 3h1jh313j23j12 h12h313 h3123h13 31h3h131 1h23h123 123h1231 h312h313h");
        System.out.println(" h3hh131h3h123 h12h313 h3123h13 31h3h131 1h23h123 123h1231 h312h313h");

        System.out.println("           /^\\/^\\\n" +
                            "         _|__|  O|\n" +
                            "\\/     /~     \\_/ \\\n" +
                            " \\____|__________/  \\\n" +
                            "        \\_______      \\\n" +
                            "                `\\     \\                 \\\n" +
                            "                  ||||   |                  \\\n" +
                            "                 ///   /                    \\\n" +
                            "                /     /                       \\\\\n" +
                            "              /      /                         \\ \\\n" +
                            "             /     /                            \\  \\\n" +
                            "           /     /             _----_            \\   \\\n" +
                            "          /     /           _-~      ~-_         |   |\n" +
                            "         (      (        _-~    _--_    ~-_     _/   |\n" +
                            "          \\      ~-____-~    _-~    ~-_    ~-_-~    /\n" +
                            "            ~-_           _-~          ~-_       _-~\n" +
                            "               ~--______-~                ~-___-~");
        System.out.println("Running ::::::::::::::::::: ");
        server.awaitTermination();
    }
}
