// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anyProto.proto

package Game.Core;

public final class AnyProto {
  private AnyProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface AnyRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:AnyRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string type_url = 1;</code>
     */
    java.lang.String getTypeUrl();
    /**
     * <code>optional string type_url = 1;</code>
     */
    com.google.protobuf.ByteString
        getTypeUrlBytes();

    /**
     * <code>optional bytes value = 2;</code>
     */
    com.google.protobuf.ByteString getValue();
  }
  /**
   * Protobuf type {@code AnyRequest}
   */
  public  static final class AnyRequest extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:AnyRequest)
      AnyRequestOrBuilder {
    // Use AnyRequest.newBuilder() to construct.
    private AnyRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
    }
    private AnyRequest() {
      typeUrl_ = "";
      value_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private AnyRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              typeUrl_ = s;
              break;
            }
            case 18: {

              value_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw new RuntimeException(e.setUnfinishedMessage(this));
      } catch (java.io.IOException e) {
        throw new RuntimeException(
            new com.google.protobuf.InvalidProtocolBufferException(
                e.getMessage()).setUnfinishedMessage(this));
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Game.Core.AnyProto.internal_static_AnyRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Game.Core.AnyProto.internal_static_AnyRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Game.Core.AnyProto.AnyRequest.class, Game.Core.AnyProto.AnyRequest.Builder.class);
    }

    public static final int TYPE_URL_FIELD_NUMBER = 1;
    private volatile java.lang.Object typeUrl_;
    /**
     * <code>optional string type_url = 1;</code>
     */
    public java.lang.String getTypeUrl() {
      java.lang.Object ref = typeUrl_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        typeUrl_ = s;
        return s;
      }
    }
    /**
     * <code>optional string type_url = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTypeUrlBytes() {
      java.lang.Object ref = typeUrl_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        typeUrl_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int VALUE_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString value_;
    /**
     * <code>optional bytes value = 2;</code>
     */
    public com.google.protobuf.ByteString getValue() {
      return value_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getTypeUrlBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessage.writeString(output, 1, typeUrl_);
      }
      if (!value_.isEmpty()) {
        output.writeBytes(2, value_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getTypeUrlBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessage.computeStringSize(1, typeUrl_);
      }
      if (!value_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, value_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    public static Game.Core.AnyProto.AnyRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static Game.Core.AnyProto.AnyRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static Game.Core.AnyProto.AnyRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Game.Core.AnyProto.AnyRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(Game.Core.AnyProto.AnyRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code AnyRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:AnyRequest)
        Game.Core.AnyProto.AnyRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Game.Core.AnyProto.internal_static_AnyRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Game.Core.AnyProto.internal_static_AnyRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Game.Core.AnyProto.AnyRequest.class, Game.Core.AnyProto.AnyRequest.Builder.class);
      }

      // Construct using game.core.protobuf.AnyProto.AnyRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        typeUrl_ = "";

        value_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Game.Core.AnyProto.internal_static_AnyRequest_descriptor;
      }

      public Game.Core.AnyProto.AnyRequest getDefaultInstanceForType() {
        return Game.Core.AnyProto.AnyRequest.getDefaultInstance();
      }

      public Game.Core.AnyProto.AnyRequest build() {
        Game.Core.AnyProto.AnyRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public Game.Core.AnyProto.AnyRequest buildPartial() {
        Game.Core.AnyProto.AnyRequest result = new Game.Core.AnyProto.AnyRequest(this);
        result.typeUrl_ = typeUrl_;
        result.value_ = value_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Game.Core.AnyProto.AnyRequest) {
          return mergeFrom((Game.Core.AnyProto.AnyRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Game.Core.AnyProto.AnyRequest other) {
        if (other == Game.Core.AnyProto.AnyRequest.getDefaultInstance()) return this;
        if (!other.getTypeUrl().isEmpty()) {
          typeUrl_ = other.typeUrl_;
          onChanged();
        }
        if (other.getValue() != com.google.protobuf.ByteString.EMPTY) {
          setValue(other.getValue());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Game.Core.AnyProto.AnyRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Game.Core.AnyProto.AnyRequest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object typeUrl_ = "";
      /**
       * <code>optional string type_url = 1;</code>
       */
      public java.lang.String getTypeUrl() {
        java.lang.Object ref = typeUrl_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          typeUrl_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string type_url = 1;</code>
       */
      public com.google.protobuf.ByteString
          getTypeUrlBytes() {
        java.lang.Object ref = typeUrl_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          typeUrl_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string type_url = 1;</code>
       */
      public Builder setTypeUrl(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        typeUrl_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string type_url = 1;</code>
       */
      public Builder clearTypeUrl() {
        
        typeUrl_ = getDefaultInstance().getTypeUrl();
        onChanged();
        return this;
      }
      /**
       * <code>optional string type_url = 1;</code>
       */
      public Builder setTypeUrlBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        typeUrl_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString value_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes value = 2;</code>
       */
      public com.google.protobuf.ByteString getValue() {
        return value_;
      }
      /**
       * <code>optional bytes value = 2;</code>
       */
      public Builder setValue(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes value = 2;</code>
       */
      public Builder clearValue() {
        
        value_ = getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:AnyRequest)
    }

    // @@protoc_insertion_point(class_scope:AnyRequest)
    private static final Game.Core.AnyProto.AnyRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new Game.Core.AnyProto.AnyRequest();
    }

    public static Game.Core.AnyProto.AnyRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AnyRequest>
        PARSER = new com.google.protobuf.AbstractParser<AnyRequest>() {
      public AnyRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        try {
          return new AnyRequest(input, extensionRegistry);
        } catch (RuntimeException e) {
          if (e.getCause() instanceof
              com.google.protobuf.InvalidProtocolBufferException) {
            throw (com.google.protobuf.InvalidProtocolBufferException)
                e.getCause();
          }
          throw e;
        }
      }
    };

    public static com.google.protobuf.Parser<AnyRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AnyRequest> getParserForType() {
      return PARSER;
    }

    public Game.Core.AnyProto.AnyRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_AnyRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_AnyRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016anyProto.proto\"-\n\nAnyRequest\022\020\n\010type_u" +
      "rl\030\001 \001(\t\022\r\n\005value\030\002 \001(\014B\036\n\022game.core.pro" +
      "tobufB\010AnyProtob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_AnyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AnyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_AnyRequest_descriptor,
        new java.lang.String[] { "TypeUrl", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}