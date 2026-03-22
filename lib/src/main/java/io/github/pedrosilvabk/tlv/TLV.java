package io.github.pedrosilvabk.tlv;

public class TLV {
    private final TLVParser parser;
    private final TLVBuilder builder;

    public TLV(TLVParser parser, TLVBuilder builder) {
        this.parser = parser;
        this.builder = builder;
    }

    public TLVBuilder getBuilder() {
        return builder;
    }

    public TLVParser getParser() {
        return parser;
    }
}
