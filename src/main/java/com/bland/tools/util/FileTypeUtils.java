package com.bland.tools.util;

/**
 * @author cqk
 * @since 2022/7/23
 */
public class FileTypeUtils {

    /**
     * png文件尾标识
     */
    public static final String PNG_FILE_END_HEX_STRING = "AE426082";

    /**
     * 不可见字符
     */
    public static String ZERO_WIDTH_SPACE_UNICODE_STRING = "200b";
    public static String ZERO_WIDTH_NON_JOINER_UNICODE_STRING = "200c";
    public static String ZERO_WIDTH_JOINER_UNICODE_STRING = "200d";

    public static int findSubByteArray(byte[] srcBytes, byte[] subBytes) {
        return findSubByteArray(srcBytes, subBytes, 0, srcBytes.length - 1);
    }

    /**
     * 查找子数组在原数组中的位置，如果没有返回-1
     *
     * @param srcBytes 原数组
     * @param subBytes 子数组
     * @param start    原数组查询的开始位置
     * @param end      原数组查询的结束位置
     * @return 子数组在原数组中的位置，如果不存在子数组返回-1
     */
    public static int findSubByteArray(byte[] srcBytes, byte[] subBytes, int start, int end) {
        int index1 = start;
        int index2 = 0;
        if (subBytes != null) {
            while (index1 < srcBytes.length && index1 < end) {
                int offset = 0;
                while (srcBytes[index1 + offset] == subBytes[index2 + offset]) {
                    if (index2 + offset + 1 >= subBytes.length)
                        return index1;
                    offset++;
                    if (index1 + offset >= srcBytes.length || index2 + offset >= subBytes.length)
                        break;
                }
                index1++;
            }
            return -1;
        } else
            return index1;
    }


}
