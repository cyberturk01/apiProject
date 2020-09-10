package com.project1.utilities;

/**
 * Created in Project1 by Gokhan on Apr, 2020
 */
public class JavaUtility {

    public static void reverse(byte[] values) {
        reverse(values, 0, values.length);
    }

    public static void reverse(byte[] values, int offset, int length) {
        int aidx = offset;
        int bidx = offset + length - 1;
        while (bidx > aidx) {
            byte value = values[aidx];
            values[aidx] = values[bidx];
            values[bidx] = value;
            ++aidx;
            --bidx;
        }
    }

    public static void reverse(int[] values) {
        reverse(values, 0, values.length);
    }

    public static void reverse(int[] values, int offset, int length) {
        int aidx = offset;
        int bidx = offset + length - 1;
        while (bidx > aidx) {
            int value = values[aidx];
            values[aidx] = values[bidx];
            values[bidx] = value;
            ++aidx;
            --bidx;
        }
    }

    public static int indexOf(int[] values, int value) {
        int count = (values == null) ? 0 : values.length;
        for (int ii = 0; ii < count; ++ii) {
            if (values[ii] == value) {
                return ii;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] values, byte value) {
        int count = (values == null) ? 0 : values.length;
        for (int ii = 0; ii < count; ++ii) {
            if (values[ii] == value) {
                return ii;
            }
        }
        return -1;
    }

    public static int indexOf(float[] values, float value) {
        int count = (values == null) ? 0 : values.length;
        for (int ii = 0; ii < count; ++ii) {
            if (values[ii] == value) {
                return ii;
            }
        }
        return -1;
    }
}
