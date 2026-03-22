package io.github.pedrosilvabk.utils;

public class TLVUtils {
    public static byte[] encodeLength(int arrLength) {
        if (arrLength < 0x80) {
            return new byte[]{(byte) arrLength};
        } else if (arrLength <= 0xFF) {
            return new byte[]{(byte) 0x81, (byte) arrLength};
        } else if (arrLength <= 0xFFFF) {
            return new byte[]{(byte) 0x82, (byte) (arrLength >> 8), (byte) arrLength};
        } else if (arrLength <= 0xFFFFFF) {
            return new byte[]{(byte) 0x83, (byte) (arrLength >> 16), (byte) (arrLength >> 8), (byte) arrLength};
        } else {
            return new byte[]{(byte) 0x84, (byte) (arrLength >> 24), (byte) (arrLength >> 16), (byte) (arrLength >> 8), (byte) arrLength};
        }
    }

    public static int[] decodeTag(byte[] data, int offset) {
        int firstByte = data[offset] & 0xFF;
        if ((firstByte & 0x1F) != 0x1F) {
            return new int[]{firstByte, offset + 1};
        }
        int tag = firstByte;
        do {
            offset++;
            int nextByte = data[offset] & 0xFF;
            tag = (tag << 8) | nextByte;
            if ((nextByte & 0x80) == 0) {
                offset++;
                break;
            }
        } while (true);
        return new int[]{tag, offset};
    }

    public static int[] decodeLength(byte[] data, int offset) {
        int firstByte = data[offset] & 0xFF;
        if (firstByte < 0x80) {
            return new int[]{firstByte, offset + 1};
        }
        int numBytes = firstByte & 0x7F;
        int length = 0;
        for (int i = 0; i < numBytes; i++) {
            length = (length << 8) | (data[offset + 1 + i] & 0xFF);
        }
        return new int[]{length, offset + 1 + numBytes};
    }

    public static byte[] encodeTag(int tag) {
        if (tag < 0) {
            throw new IllegalArgumentException("Tag must be non-negative");
        }
        if (tag <= 0xFF) {
            return new byte[]{(byte) tag};
        } else if (tag <= 0xFFFF) {
            return new byte[]{
                    (byte) ((tag >> 8) & 0xFF),
                    (byte) (tag & 0xFF)
            };
        } else if (tag <= 0xFFFFFF) {
            return new byte[]{
                    (byte) ((tag >> 16) & 0xFF),
                    (byte) ((tag >> 8) & 0xFF),
                    (byte) (tag & 0xFF)
            };
        } else {
            return new byte[]{
                    (byte) ((tag >> 24) & 0xFF),
                    (byte) ((tag >> 16) & 0xFF),
                    (byte) ((tag >> 8) & 0xFF),
                    (byte) (tag & 0xFF)
            };
        }
    }

    public static byte[] concat(byte[]... arrs) {
        int totalLength = 0;
        for (byte[] arr : arrs) {
            totalLength += arr.length;
        }
        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] arr : arrs) {
            System.arraycopy(arr, 0, result, offset, arr.length);
            offset += arr.length;
        }
        return result;
    }
}