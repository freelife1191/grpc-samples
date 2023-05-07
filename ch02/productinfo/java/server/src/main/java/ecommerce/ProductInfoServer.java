package ecommerce;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class ProductInfoServer {

    private static final Logger logger = Logger.getLogger(ProductInfoServer.class.getName());

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        /**
         * 서버 인스턴스는 포트 50051로 생성되는데, 이 포트는 서버가 바인딩해 메시지를 수신하는 포트다
         * 그리고 ProductInfo 서비스 구현이 서버에 추가됐다
         */
        server = ServerBuilder.forPort(port)
                .addService(new ProductInfoImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        /**
         * JVM 종료시 gRPC 서버를 종료하고자 런타임 종료 후크가 추가된다
         */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            logger.info("*** shutting down gRPC server since JVM is shutting down");
            ProductInfoServer.this.stop();
            logger.info("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            /**
             * 메서드 끝에서 서버 스레드는 서버가 종료될 때까지 대기한다
             */
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProductInfoServer server = new ProductInfoServer();
        server.start();
        server.blockUntilShutdown();
    }

}
