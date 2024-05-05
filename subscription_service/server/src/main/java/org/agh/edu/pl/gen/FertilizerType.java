// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: greenhouse_conditions.proto

// Protobuf Java Version: 4.26.1
package org.agh.edu.pl.gen;

/**
 * Protobuf enum {@code FertilizerType}
 */
public enum FertilizerType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>ORGANIC = 0;</code>
   */
  ORGANIC(0),
  /**
   * <code>INORGANIC = 1;</code>
   */
  INORGANIC(1),
  /**
   * <code>LIQUID = 2;</code>
   */
  LIQUID(2),
  /**
   * <code>GRANULAR = 3;</code>
   */
  GRANULAR(3),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      FertilizerType.class.getName());
  }
  /**
   * <code>ORGANIC = 0;</code>
   */
  public static final int ORGANIC_VALUE = 0;
  /**
   * <code>INORGANIC = 1;</code>
   */
  public static final int INORGANIC_VALUE = 1;
  /**
   * <code>LIQUID = 2;</code>
   */
  public static final int LIQUID_VALUE = 2;
  /**
   * <code>GRANULAR = 3;</code>
   */
  public static final int GRANULAR_VALUE = 3;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static FertilizerType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static FertilizerType forNumber(int value) {
    switch (value) {
      case 0: return ORGANIC;
      case 1: return INORGANIC;
      case 2: return LIQUID;
      case 3: return GRANULAR;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<FertilizerType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      FertilizerType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<FertilizerType>() {
          public FertilizerType findValueByNumber(int number) {
            return FertilizerType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return GreenhouseConditionsProto.getDescriptor().getEnumTypes().get(2);
  }

  private static final FertilizerType[] VALUES = values();

  public static FertilizerType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private FertilizerType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:FertilizerType)
}
