package com.lyrics.ecs.utils;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    /**
     * 判断对象是否为空
     * 支持 String、Collection、Map、数组、普通对象
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj.getClass().isArray()) {
            return ((Object[]) obj).length == 0;
        }

        // 其他对象只要不为 null 就认为非空
        return false;
    }

    /**
     * 判断对象是否非空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
