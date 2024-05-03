package org.agh.edu.pl.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: greenhouse_conditions.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GreenhouseConditionsGrpc {

  private GreenhouseConditionsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "GreenhouseConditions";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<GreenhouseConditionsSubscription,
          Conditions> getSubscribeGreenhouseConditionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeGreenhouseConditions",
      requestType = GreenhouseConditionsSubscription.class,
      responseType = Conditions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<GreenhouseConditionsSubscription,
          Conditions> getSubscribeGreenhouseConditionsMethod() {
    io.grpc.MethodDescriptor<GreenhouseConditionsSubscription, Conditions> getSubscribeGreenhouseConditionsMethod;
    if ((getSubscribeGreenhouseConditionsMethod = GreenhouseConditionsGrpc.getSubscribeGreenhouseConditionsMethod) == null) {
      synchronized (GreenhouseConditionsGrpc.class) {
        if ((getSubscribeGreenhouseConditionsMethod = GreenhouseConditionsGrpc.getSubscribeGreenhouseConditionsMethod) == null) {
          GreenhouseConditionsGrpc.getSubscribeGreenhouseConditionsMethod = getSubscribeGreenhouseConditionsMethod =
              io.grpc.MethodDescriptor.<GreenhouseConditionsSubscription, Conditions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeGreenhouseConditions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GreenhouseConditionsSubscription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Conditions.getDefaultInstance()))
              .setSchemaDescriptor(new GreenhouseConditionsMethodDescriptorSupplier("SubscribeGreenhouseConditions"))
              .build();
        }
      }
    }
    return getSubscribeGreenhouseConditionsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreenhouseConditionsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreenhouseConditionsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreenhouseConditionsStub>() {
        @java.lang.Override
        public GreenhouseConditionsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreenhouseConditionsStub(channel, callOptions);
        }
      };
    return GreenhouseConditionsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreenhouseConditionsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreenhouseConditionsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreenhouseConditionsBlockingStub>() {
        @java.lang.Override
        public GreenhouseConditionsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreenhouseConditionsBlockingStub(channel, callOptions);
        }
      };
    return GreenhouseConditionsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreenhouseConditionsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreenhouseConditionsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreenhouseConditionsFutureStub>() {
        @java.lang.Override
        public GreenhouseConditionsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreenhouseConditionsFutureStub(channel, callOptions);
        }
      };
    return GreenhouseConditionsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void subscribeGreenhouseConditions(GreenhouseConditionsSubscription request,
                                               io.grpc.stub.StreamObserver<Conditions> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeGreenhouseConditionsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GreenhouseConditions.
   */
  public static abstract class GreenhouseConditionsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GreenhouseConditionsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GreenhouseConditions.
   */
  public static final class GreenhouseConditionsStub
      extends io.grpc.stub.AbstractAsyncStub<GreenhouseConditionsStub> {
    private GreenhouseConditionsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreenhouseConditionsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreenhouseConditionsStub(channel, callOptions);
    }

    /**
     */
    public void subscribeGreenhouseConditions(GreenhouseConditionsSubscription request,
                                              io.grpc.stub.StreamObserver<Conditions> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeGreenhouseConditionsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GreenhouseConditions.
   */
  public static final class GreenhouseConditionsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GreenhouseConditionsBlockingStub> {
    private GreenhouseConditionsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreenhouseConditionsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreenhouseConditionsBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<Conditions> subscribeGreenhouseConditions(
        GreenhouseConditionsSubscription request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeGreenhouseConditionsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GreenhouseConditions.
   */
  public static final class GreenhouseConditionsFutureStub
      extends io.grpc.stub.AbstractFutureStub<GreenhouseConditionsFutureStub> {
    private GreenhouseConditionsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreenhouseConditionsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreenhouseConditionsFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_GREENHOUSE_CONDITIONS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE_GREENHOUSE_CONDITIONS:
          serviceImpl.subscribeGreenhouseConditions((GreenhouseConditionsSubscription) request,
              (io.grpc.stub.StreamObserver<Conditions>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubscribeGreenhouseConditionsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
                    GreenhouseConditionsSubscription,
                    Conditions>(
                service, METHODID_SUBSCRIBE_GREENHOUSE_CONDITIONS)))
        .build();
  }

  private static abstract class GreenhouseConditionsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreenhouseConditionsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GreenhouseConditionsProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GreenhouseConditions");
    }
  }

  private static final class GreenhouseConditionsFileDescriptorSupplier
      extends GreenhouseConditionsBaseDescriptorSupplier {
    GreenhouseConditionsFileDescriptorSupplier() {}
  }

  private static final class GreenhouseConditionsMethodDescriptorSupplier
      extends GreenhouseConditionsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GreenhouseConditionsMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreenhouseConditionsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreenhouseConditionsFileDescriptorSupplier())
              .addMethod(getSubscribeGreenhouseConditionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
