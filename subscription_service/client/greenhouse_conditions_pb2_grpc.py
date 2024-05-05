# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import greenhouse_conditions_pb2 as greenhouse__conditions__pb2


class GreenhouseConditionsStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.SubscribeGreenhouseConditions = channel.unary_stream(
                '/GreenhouseConditions/SubscribeGreenhouseConditions',
                request_serializer=greenhouse__conditions__pb2.GreenhouseConditionsSubscription.SerializeToString,
                response_deserializer=greenhouse__conditions__pb2.Conditions.FromString,
                )


class GreenhouseConditionsServicer(object):
    """Missing associated documentation comment in .proto file."""

    def SubscribeGreenhouseConditions(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_GreenhouseConditionsServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'SubscribeGreenhouseConditions': grpc.unary_stream_rpc_method_handler(
                    servicer.SubscribeGreenhouseConditions,
                    request_deserializer=greenhouse__conditions__pb2.GreenhouseConditionsSubscription.FromString,
                    response_serializer=greenhouse__conditions__pb2.Conditions.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'GreenhouseConditions', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class GreenhouseConditions(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def SubscribeGreenhouseConditions(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(request, target, '/GreenhouseConditions/SubscribeGreenhouseConditions',
            greenhouse__conditions__pb2.GreenhouseConditionsSubscription.SerializeToString,
            greenhouse__conditions__pb2.Conditions.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)