package com.example.dsproject.server;

import io.grpc.Grpc;
import io.grpc.stub.StreamObserver;
import protobuf.java.ChatServiceGrpc;
import protobuf.java.MessageRequest;
import protobuf.java.MessageResponse;


public class Server extends ChatServiceGrpc.ChatServiceImplBase {
    @Override
    public void message(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        String message = request.getMessage();
        System.out.println("Received message:" + message);
        MessageResponse messageResponse = MessageResponse.newBuilder().setMessage("Received your" + message + "Hello from Server")
                .build();
        responseObserver.onNext(messageResponse);
        responseObserver.onCompleted();
    }
}
