package io.github.pedrosilvabk.codec;

import io.github.pedrosilvabk.annotation.Codec;
import io.github.pedrosilvabk.annotation.NativeCodec;
import io.github.pedrosilvabk.registry.ValueTLVCodec;

import java.nio.charset.StandardCharsets;

@Codec
@NativeCodec
public class StringCodec implements ValueTLVCodec<String> {
    @Override
    public Class<String> type() {
        return String.class;
    }

    @Override
    public byte[] encode(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String decode(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
