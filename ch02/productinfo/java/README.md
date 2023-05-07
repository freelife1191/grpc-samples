## ``ProductInfo`` Service and Client - Java Implementation

### Building and Running Service

In order to build gradle project, Go to ``Java`` project root directory location (samples) and execute
 the following shell command,
```
./gradlew :ch02:productinfo:java:server:build
```

In order to run, Go to ``Java`` project root directory location (productinfo/java/server) and execute the following
shell command,

```
java -jar build/libs/server.jar
```

### Building and Running Client

In order to build gradle project, Go to ``Java`` project root directory location (inside samples directory) and execute
 the following shell command,
```
./gradlew :ch02:productinfo:java:client:build
```

In order to run, Go to ``Java`` project root directory location (productinfo/java/client) and execute the following
shell command,

```
java -jar build/libs/client.jar
```


```
$ java -jar server/build/libs/server.jar
5월 07, 2023 8:17:21 오후 ecommerce.ProductInfoServer start
정보: Server started, listening on 50051
```

```
$ java -jar client/build/libs/client.jar
5월 07, 2023 8:17:27 오후 ecommerce.ProductInfoClient main
정보: Product ID: 3095ffe7-b4bc-40a6-9875-c21c1ab1cdd9 added successfully.
5월 07, 2023 8:17:27 오후 ecommerce.ProductInfoClient main
정보: Product: id: "3095ffe7-b4bc-40a6-9875-c21c1ab1cdd9"
name: "Samsung S10"
description: "Samsung Galaxy S10 is the latest smart phone, launched in February 2019"
price: 700.0
```

## 요약
gRPC 애플리케이션을 개발할 때는 먼저 구조화된 데이터를 직렬화하고자 언어에 구애받지 않는 플랫폼 중립적이며 확장 가능한 메커니즘인 프로토콤 버퍼를 사용해 서비스 인터페이스를 정의했다  
다음으로 선택한 프로그래밍 언어에 대한 서버와 클라이언트 코드를 생서앻 저수준 통신 추상화를 제공함으로써 서버와 클라이언트 로직을 단순화한다  
서버에서는 원격으로 제공되는 메서드의 로직을 구현하고 서비스 바인딩하는 gPRC 서버를 실행한다  
클라이언트에서는 원격 gRPC 서버에 연결하고 생성된 클라이언트 코드를 사용해 원격 메서드를 호출한다

2장에서는 주로 gRPC 서버와 클라이언트 애플리케이션의 개발과 실행에 대한 실무 경험을 제공한다  
예제를 따라함으로써 얻는 경험은 실제 gRPC 애플리케이션을 구축 할 때 매우 유용한데, 사용하는 언어에 관계없이 gRPC 애플리케이션을 구축하는 데 비슷한 단계들을 거치기 때문이다