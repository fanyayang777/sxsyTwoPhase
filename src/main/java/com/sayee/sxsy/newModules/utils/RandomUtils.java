package com.sayee.sxsy.newModules.utils;

/**
 * 随机数工具类
 */
public class RandomUtils {
    //生成6随机数
    public Integer Random() {
        int random = (int) (Math.random() * (999999 - 100000 + 1)) + 100000;//产生100000-999999的随机数
        return random;
    }
}
