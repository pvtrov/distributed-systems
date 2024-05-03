chmod a+x protoc-gen-grpc-java-1.62.2-linux-x86_64.exe

protoc -I. --java_out=src/main/java/org/agh/edu/pl/gen --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.62.2-linux-x86_64.exe --grpc-java_out=src/main/java/org/agh/edu/pl/gen greenhouse_conditions.proto