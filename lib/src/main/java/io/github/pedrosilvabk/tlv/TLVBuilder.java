package io.github.pedrosilvabk.tlv;


import io.github.pedrosilvabk.registry.CodecRegistry;
import io.github.pedrosilvabk.registry.ObjectTLVCodec;
import io.github.pedrosilvabk.registry.ValueTLVCodec;

public class TLVBuilder {
    private final CodecRegistry codecRegistry;

    public TLVBuilder(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    public byte[] parse(Object object) {
        if (object.getClass().isPrimitive()) {
            assert codecRegistry.getValue(object.getClass()) != null;

            return encodeWith(codecRegistry.getValue(object.getClass()), object);
        }
        else {
            assert codecRegistry.getObject(object.getClass()) != null;
            return encodeWith(codecRegistry.getObject(object.getClass()), object);
        }
    }

    private <T> byte[] encodeWith(ObjectTLVCodec<T> codec, Object object) {
        return codec.encode((T) object);
    }

    private <T> byte[] encodeWith(ValueTLVCodec<T> codec, Object object) {
        return codec.encode((T) object);
    }
}
