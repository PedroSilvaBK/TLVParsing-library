package io.github.pedrosilvabk.tlv;

import org.example.registry.CodecRegistry;
import org.example.registry.ObjectTLVCodec;
import org.example.registry.ValueTLVCodec;
import org.example.utils.TLVUtils;

public class TLVParser {
    private final CodecRegistry codecRegistry;

    public TLVParser(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    public <T> T parse(byte[] tlv, Class<T> targetClass) {
        if (targetClass.isPrimitive()) {
            return decodeWith(codecRegistry.getValue(targetClass), tlv);
        }
        else {
            return decodeWith(codecRegistry.getObject(targetClass), tlv);
        }
    }

    private <T> T decodeWith(ObjectTLVCodec<T> codec, byte[] tlv) {
        int[] tagResult = TLVUtils.decodeTag(tlv, 0);
        int[] lengthResult = TLVUtils.decodeLength(tlv, tagResult[1]);
        return codec.decode(tlv, lengthResult[1], lengthResult[0]);
    }

    private <T> T decodeWith(ValueTLVCodec<T> codec, byte[] tlv) {
        return codec.decode(tlv);
    }
}
