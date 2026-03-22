package io.github.pedrosilvabk.registry;

public interface ValueTLVCodec<T> {
    Class<T> type();
    byte[] encode(T value);
    T decode(byte[] bytes);
}
