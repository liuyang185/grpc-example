package com.example.grpc;

import com.example.grpc.gencode.HelloRequest;
import com.example.grpc.gencode.HelloResponse;
import com.example.grpc.gencode.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author YCKJ1423
 * @Date 2020/1/7 14:01
 * Version 1.0
 **/
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext(true).build();

            HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

            HelloResponse helloResponse = stub
                    .hello(HelloRequest.newBuilder().setFirstName("Baeldung").setLastName("gRPC").build());

            System.out.println(helloResponse.getGreeting());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
