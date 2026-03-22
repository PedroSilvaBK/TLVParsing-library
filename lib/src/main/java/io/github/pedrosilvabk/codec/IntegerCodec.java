package io.github.pedrosilvabk.codec;


import io.github.pedrosilvabk.annotation.Codec;
import io.github.pedrosilvabk.annotation.NativeCodec;
import io.github.pedrosilvabk.registry.ValueTLVCodec;

@Codec
@NativeCodec
public class IntegerCodec implements ValueTLVCodec<Integer> {
    @Override
    public Class<Integer> type() {
        return Integer.TYPE;
    }

    @Override
    public byte[] encode(Integer value) {
        byte[] bytes = new byte[4];
        for (int i = 3; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return bytes;
    }

    @Override
    public Integer decode(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value = (value << 8) | (bytes[i] & 0xFF);
        }
        return value;
    }
}
