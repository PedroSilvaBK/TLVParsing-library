package io.github.pedrosilvabk.codec;


import io.github.pedrosilvabk.annotation.Codec;
import io.github.pedrosilvabk.annotation.NativeCodec;
import io.github.pedrosilvabk.registry.ValueTLVCodec;

@Codec
@NativeCodec
public class BooleanCodec implements ValueTLVCodec<Boolean> {
    @Override
    public Class<Boolean> type() {
        return Boolean.TYPE;
    }

    @Override
    public byte[] encode(Boolean value) {
        return new byte[]{(byte) (value ? 1 : 0)};
    }

    @Override
    public Boolean decode(byte[] bytes) {
        return bytes.length > 0 && bytes[0] != 0;
    }
}
