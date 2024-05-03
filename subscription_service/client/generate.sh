#!/bin/bash

if [ ! -f "greenhouse_conditions.proto" ]; then
    echo "Protobuf file 'flowers_generate.proto' do not exist."
    exit 1
fi

python -m grpc_tools.protoc --proto_path=. --python_out=. --grpc_python_out=. greenhouse_conditions.proto

echo "Python files generated."