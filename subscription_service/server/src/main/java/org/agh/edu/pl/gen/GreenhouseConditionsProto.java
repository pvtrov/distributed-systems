// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: greenhouse_conditions.proto
// Protobuf Java Version: 4.26.1

package org.agh.edu.pl.gen;

public final class GreenhouseConditionsProto {
  private GreenhouseConditionsProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      GreenhouseConditionsProto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GreenhouseConditionsSubscription_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GreenhouseConditionsSubscription_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Conditions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Conditions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Light_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Light_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Soil_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Soil_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033greenhouse_conditions.proto\"I\n Greenho" +
      "useConditionsSubscription\022\023\n\013greenhouses" +
      "\030\001 \003(\t\022\020\n\010clientID\030\002 \001(\005\"\374\001\n\nConditions\022" +
      "\022\n\ngreenhouse\030\001 \001(\t\022\023\n\013temperature\030\002 \001(\002" +
      "\022\020\n\010humidity\030\003 \001(\002\022\022\n\nis_daytime\030\004 \001(\010\022\025" +
      "\n\005light\030\005 \003(\0132\006.Light\022\023\n\004soil\030\006 \003(\0132\005.So" +
      "il\022\021\n\tco2_level\030\007 \001(\002\022\026\n\016nitrogen_level\030" +
      "\010 \001(\002\022\036\n\nplant_type\030\t \001(\0162\n.PlantType\022(\n" +
      "\017fertilizer_type\030\n \001(\0162\017.FertilizerType\"" +
      "1\n\005Light\022\021\n\tintensity\030\001 \001(\002\022\025\n\005color\030\002 \001" +
      "(\0162\006.Color\"0\n\004Soil\022\026\n\016moisture_level\030\001 \001" +
      "(\002\022\020\n\010pH_level\030\002 \001(\002*0\n\005Color\022\007\n\003RED\020\000\022\010" +
      "\n\004BLUE\020\001\022\t\n\005WHITE\020\002\022\t\n\005GREEN\020\003*;\n\tPlantT" +
      "ype\022\n\n\006FLOWER\020\000\022\r\n\tVEGETABLE\020\001\022\010\n\004HERB\020\002" +
      "\022\t\n\005FRUIT\020\003*F\n\016FertilizerType\022\013\n\007ORGANIC" +
      "\020\000\022\r\n\tINORGANIC\020\001\022\n\n\006LIQUID\020\002\022\014\n\010GRANULA" +
      "R\020\0032i\n\024GreenhouseConditions\022Q\n\035Subscribe" +
      "GreenhouseConditions\022!.GreenhouseConditi" +
      "onsSubscription\032\013.Conditions0\001B\"\n\003genB\031G" +
      "reenhouseConditionsProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_GreenhouseConditionsSubscription_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GreenhouseConditionsSubscription_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_GreenhouseConditionsSubscription_descriptor,
        new java.lang.String[] { "Greenhouses", "ClientID", });
    internal_static_Conditions_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Conditions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Conditions_descriptor,
        new java.lang.String[] { "Greenhouse", "Temperature", "Humidity", "IsDaytime", "Light", "Soil", "Co2Level", "NitrogenLevel", "PlantType", "FertilizerType", });
    internal_static_Light_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Light_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Light_descriptor,
        new java.lang.String[] { "Intensity", "Color", });
    internal_static_Soil_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Soil_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Soil_descriptor,
        new java.lang.String[] { "MoistureLevel", "PHLevel", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
