Generate java code from proto.

```shell script
protoc --plugin=protoc-gen-grpc-java=$PROTOC_PLUGIN_JAVA  --proto_path=./proto --grpc-java_out=./java ./proto/helloWorld.proto 

protoc -I=./proto --java_out=./java ./proto/helloWorld.proto 
```

As for the `protoc-gen-grpc-java` plugin, either can download form the maven central repo or build from [grpc-java](https://github.com/grpc/grpc-java) source.

```shell script

$ cd $GRPC_JAVA_ROOT/compiler

# Add paramter -PskipAndroid=true to avoid building android project 
$ ../gradlew java_pluginExecutable -PskipAndroid=true 

```

To compile a proto file and generate Java interfaces out of the service definitions:

```shell script

$ protoc --plugin=protoc-gen-grpc-java=build/exe/java_plugin/protoc-gen-grpc-java \
  --grpc-java_out="$OUTPUT_FILE" --proto_path="$DIR_OF_PROTO_FILE" "$PROTO_FILE"
```

To generate Java interfaces with protobuf lite:

```shell script

$ protoc --plugin=protoc-gen-grpc-java=build/exe/java_plugin/protoc-gen-grpc-java \
  --grpc-java_out=lite:"$OUTPUT_FILE" --proto_path="$DIR_OF_PROTO_FILE" "$PROTO_FILE"
```