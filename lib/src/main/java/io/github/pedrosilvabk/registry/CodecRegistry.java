package io.github.pedrosilvabk.registry;

import java.util.HashMap;
import java.util.Map;

public final class CodecRegistry {
    private final Map<String, ValueTLVCodec<?>> valueCodecs = new HashMap<>();
    private final Map<String, ObjectTLVCodec<?>> objectCodecs = new HashMap<>();
    private final Map<String, ObjectTLVCodec<?>> namedCodecs = new HashMap<>();

    public <T> void registerValue(ValueTLVCodec<T> codec) {
        valueCodecs.put(codec.type().getName(), codec);
    }

    public <T> void registerObject(ObjectTLVCodec<T> codec) {
        objectCodecs.put(codec.type().getName(), codec);
        namedCodecs.put(codec.getClass().getName(), codec);
    }

    public <T> void registerCustomCodec(ObjectTLVCodec<T> codec) {
        namedCodecs.put(codec.getClass().getName(), codec);
    }


    public <T> ValueTLVCodec<T> getValue(Class<T> type) {
        return (ValueTLVCodec<T>) valueCodecs.get(type.getName());
    }

    // Get by target type (default codec)
    public <T> ObjectTLVCodec<T> getObject(Class<T> type) {
        return (ObjectTLVCodec<T>) objectCodecs.get(type.getName());
    }

    @SuppressWarnings("unchecked")
    public <T> ObjectTLVCodec<T> getCodec(Class<?> codecClass) {
        return (ObjectTLVCodec<T>) namedCodecs.get(codecClass.getName());
    }
}