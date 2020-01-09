package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @Description
 * @Author YCKJ1423
 * @Date 2020/1/7 15:16
 * Version 1.0
 **/
public class ServerStart {
    public static void main(String[] args) {
        try {

            int port = 50051;
            final Server server = ServerBuilder.forPort(port)
                    .addService(new HelloWorldServer())
                    .build()
                    .start();
            System.out.println("Server started, listening on " + port);
            server.awaitTermination();

            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    System.err.println("*** JVM 关闭,导致gRPC服务关闭!");
                    server.shutdown();
                    System.err.println("*** 服务关闭");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
