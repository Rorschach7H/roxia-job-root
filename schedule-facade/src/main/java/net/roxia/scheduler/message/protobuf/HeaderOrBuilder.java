// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package net.roxia.scheduler.message.protobuf;

public interface HeaderOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Header)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string version = 1;</code>
   * @return The version.
   */
  String getVersion();
  /**
   * <code>string version = 1;</code>
   * @return The bytes for version.
   */
  com.google.protobuf.ByteString
      getVersionBytes();

  /**
   * <code>string accessKey = 2;</code>
   * @return The accessKey.
   */
  String getAccessKey();
  /**
   * <code>string accessKey = 2;</code>
   * @return The bytes for accessKey.
   */
  com.google.protobuf.ByteString
      getAccessKeyBytes();

  /**
   * <code>string group = 3;</code>
   * @return The group.
   */
  String getGroup();
  /**
   * <code>string group = 3;</code>
   * @return The bytes for group.
   */
  com.google.protobuf.ByteString
      getGroupBytes();

  /**
   * <code>string clientId = 4;</code>
   * @return The clientId.
   */
  String getClientId();
  /**
   * <code>string clientId = 4;</code>
   * @return The bytes for clientId.
   */
  com.google.protobuf.ByteString
      getClientIdBytes();

  /**
   * <code>.MessageType type = 5;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.MessageType type = 5;</code>
   * @return The type.
   */
  MessageType getType();
}