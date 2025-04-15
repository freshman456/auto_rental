package com.rental.auto_rental.utils;

import cn.hutool.extra.pinyin.PinyinUtil;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/15
 */
public class PinYinUtils {
    public static String getPinYinHeadChar(String str) {
        return PinyinUtil.getFirstLetter(str,"").toUpperCase();
    }
}
