package ecommerce;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

/**
 * gRPC client sample for productInfo service.
 */
public class ProductInfoClient {

    private static final Logger logger = Logger.getLogger(ProductInfoClient.class.getName());

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
            /**
             * 연결하려는 서버 주소와 포트를 지정해 gRPC 채널을 만든다
             * 여기서 같은 컴퓨터에서 실행되고 포트 50051로 수신을 대기하는 서버에 연결하며,
             * 평문을 사용하는 데 클라이언트와 서버 사이에 보안되지 않은 연결을 사용한다
             */
            .forAddress("localhost", 50051)
            .usePlaintext()
            .build();

        /**
         * 새로 생성된 채널을 사용해 클라이언트 스텁을 만드는데, 2가지 유형을 사용할 수 있다
         * 하나는 서버의 응답을 받을 때까지 대기하는 BlockingStub이고, 다른 하나는 서버 응답을 기다리지 않고 옵저버를 등록해 응답을 받는 NonBlockingStub 이다
         * 여기서는 BlockingStub을 사용해 간단한 클라이언트를 사용했다
         */
        ProductInfoGrpc.ProductInfoBlockingStub stub =
                ProductInfoGrpc.newBlockingStub(channel);

        /**
         * 제품 정보와 함께 addProduct 메서드를 호출한다. 호출이 완료되면 제품ID를 반환한다
         */
        ProductInfoOuterClass.ProductID productID = stub.addProduct(
                ProductInfoOuterClass.Product.newBuilder()
                        .setName("Samsung S10")
                        .setDescription("Samsung Galaxy S10 is the latest smart phone, " +
                                "launched in February 2019")
                        .setPrice(700.0f)
                        .build());
        logger.info("Product ID: " + productID.getValue() + " added successfully.");

        /**
         * 제품 ID로 getProduct를 호출한다. 호출이 완료되면 제품 정보를 반환한다
         */
        ProductInfoOuterClass.Product product = stub.getProduct(productID);
        logger.info("Product: " + product.toString());
        /**
         * 모든 작업이 완료되면 연결을 종료해 애플리케이션에서 사용한 네트워크 리소스를 안전하게 반환한다
         */
        channel.shutdown();
    }
}
