package ecommerce;

import io.grpc.Status;
import io.grpc.StatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 플러그인에 의해 생성된 추상 클래스(ProductInfoGrpc.ProductInfoImplBase)를 확장한다
 * 이를 통해 서비스에 정의된 AddProduct와 GetProduct 메서드에 비즈니스 로직을 추가할 수 있다
 */
public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    private Map productMap = new HashMap<String, ProductInfoOuterClass.Product>();

    @Override
    public void addProduct(ProductInfoOuterClass.Product request,
                           /**
                            * AddProduct 메서드는 Product(ProductInfoOuterClass.Product)를 파라미터로 사용한다
                            * Product 클래스는 서비스 정의에 의해 생성된 ProductInfoOuterClass 클래스에 선언돼 있다
                            */
                           io.grpc.stub.StreamObserver<ProductInfoOuterClass.ProductID> responseObserver) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        request = request.toBuilder().setId(randomUUIDString).build();
        productMap.put(randomUUIDString, request);
        ProductInfoOuterClass.ProductID id
                = ProductInfoOuterClass.ProductID.newBuilder().setValue(randomUUIDString).build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductInfoOuterClass.ProductID request,
                           /**
                            * GetProduct 메서드는 ProductID(ProductInfoOuterClass.ProductID)를 파라미터로 사용한다
                            * ProductID 클래스는 서비스 정의에 의해 생성된 ProductInfoOuterClass 클래스에 선언돼 있다
                            * responseObserver 객체는 클라이언트에게 응답을 보내고 스트림을 닫는 데 사용된다
                            */
                           io.grpc.stub.StreamObserver<ProductInfoOuterClass.Product> responseObserver) {
        String id = request.getValue();
        if (productMap.containsKey(id)) {
            // 클라이언트에게 응답을 보낸다
            responseObserver.onNext((ProductInfoOuterClass.Product) productMap.get(id));
            // 스트림을 닫아 클라이언트 호출을 종료한다
            responseObserver.onCompleted();
        } else {
            // 클라이언트에게 에러를 보낸다
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }
}
