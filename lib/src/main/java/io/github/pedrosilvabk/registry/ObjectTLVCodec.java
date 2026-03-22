package io.github.pedrosilvabk.registry;

public interface ObjectTLVCodec<T> {
    Class<T> type();
    byte[] encode(T value);
    T decode(byte[] bytes, int offset, int fieldLength);
}
