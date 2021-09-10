// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package net.roxia.scheduler.message.protobuf;

/**
 * Protobuf enum {@code MessageType}
 */
public enum MessageType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   *客户端注册
   * </pre>
   *
   * <code>REG_CLIENT = 0;</code>
   */
  REG_CLIENT(0),
  /**
   * <pre>
   *客户端注销
   * </pre>
   *
   * <code>LOGOFF_CLIENT = 1;</code>
   */
  LOGOFF_CLIENT(1),
  /**
   * <pre>
   *注册任务
   * </pre>
   *
   * <code>REG_TASK = 2;</code>
   */
  REG_TASK(2),
  /**
   * <pre>
   *注销任务
   * </pre>
   *
   * <code>LOGOFF_TASK = 3;</code>
   */
  LOGOFF_TASK(3),
  /**
   * <pre>
   *执行任务
   * </pre>
   *
   * <code>EXECUTE_TASK = 4;</code>
   */
  EXECUTE_TASK(4),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   *客户端注册
   * </pre>
   *
   * <code>REG_CLIENT = 0;</code>
   */
  public static final int REG_CLIENT_VALUE = 0;
  /**
   * <pre>
   *客户端注销
   * </pre>
   *
   * <code>LOGOFF_CLIENT = 1;</code>
   */
  public static final int LOGOFF_CLIENT_VALUE = 1;
  /**
   * <pre>
   *注册任务
   * </pre>
   *
   * <code>REG_TASK = 2;</code>
   */
  public static final int REG_TASK_VALUE = 2;
  /**
   * <pre>
   *注销任务
   * </pre>
   *
   * <code>LOGOFF_TASK = 3;</code>
   */
  public static final int LOGOFF_TASK_VALUE = 3;
  /**
   * <pre>
   *执行任务
   * </pre>
   *
   * <code>EXECUTE_TASK = 4;</code>
   */
  public static final int EXECUTE_TASK_VALUE = 4;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @Deprecated
  public static MessageType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static MessageType forNumber(int value) {
    switch (value) {
      case 0: return REG_CLIENT;
      case 1: return LOGOFF_CLIENT;
      case 2: return REG_TASK;
      case 3: return LOGOFF_TASK;
      case 4: return EXECUTE_TASK;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<MessageType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      MessageType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<MessageType>() {
          public MessageType findValueByNumber(int number) {
            return MessageType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new IllegalStateException(
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
    return MessageOuterClass.getDescriptor().getEnumTypes().get(0);
  }

  private static final MessageType[] VALUES = values();

  public static MessageType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private MessageType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:MessageType)
}
