// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package net.roxia.scheduler.message.protobuf;

public interface MessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Message)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Header header = 1;</code>
   * @return Whether the header field is set.
   */
  boolean hasHeader();
  /**
   * <code>.Header header = 1;</code>
   * @return The header.
   */
  Header getHeader();
  /**
   * <code>.Header header = 1;</code>
   */
  HeaderOrBuilder getHeaderOrBuilder();

  /**
   * <code>string body = 2;</code>
   * @return The body.
   */
  String getBody();
  /**
   * <code>string body = 2;</code>
   * @return The bytes for body.
   */
  com.google.protobuf.ByteString
      getBodyBytes();
}