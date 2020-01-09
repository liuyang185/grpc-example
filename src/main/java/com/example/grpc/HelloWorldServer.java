package com.example.grpc;

import com.example.grpc.gencode.HelloRequest;
import com.example.grpc.gencode.HelloResponse;
import com.example.grpc.gencode.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @Description
 * @Author YCKJ1423
 * @Date 2020/1/7 10:13
 * Version 1.0
 **/
public class HelloWorldServer extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        StringBuilder sb = new StringBuilder();
        String greeting = sb.append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .toString();

        HelloResponse helloResponse = HelloResponse.newBuilder().setGreeting(greeting).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }



}