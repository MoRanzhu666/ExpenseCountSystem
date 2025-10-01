package com.lyrics.ecs.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.resp.system.CCodeResp;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ObjectUtils {

    /**
     * 检查对象是否为空，包括：
     * 1. null值
     * 2. 空字符串（trim后）
     * 3. 空集合/空Map
     * 4. 空数组
     * 5. 所有字段（包括父类字段）都为空的对象
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        // 字符串处理
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }

        // 集合处理
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        // 映射处理
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        // 数组处理
        if (obj.getClass().isArray()) {
            return ((Object[]) obj).length == 0;
        }

        // 基本类型包装类处理（避免反射检查）
        if (isPrimitiveWrapper(obj.getClass())) {
            return false; // 非null的包装类认为非空
        }

        // 检查所有字段（包括父类）是否都为空
        return isAllFieldsEmpty(obj);
    }

    /**
     * 检查对象及其所有父类的字段是否都为空
     */
    private static boolean isAllFieldsEmpty(Object obj) {
        try {
            // 获取所有字段（包括父类）
            List<Field> allFields = getAllFields(obj.getClass());

            for (Field field : allFields) {
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                // 如果有任何一个字段非空，则对象非空
                if (!isEmpty(fieldValue)) {
                    return false;
                }
            }
            return true; // 所有字段都为空
        } catch (IllegalAccessException e) {
            // 反射访问异常时，默认不视为空对象
            return false;
        }
    }

    /**
     * 获取类及其所有父类的字段（不包括Object类）
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;

        // 遍历所有父类，直到Object类
        while (currentClass != null && currentClass != Object.class) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (Field field : declaredFields) {
                fields.add(field);
            }
            // 移动到父类
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 判断是否为基本类型包装类
     */
    private static boolean isPrimitiveWrapper(Class<?> clazz) {
        return clazz == Integer.class || clazz == Long.class || clazz == Short.class ||
                clazz == Double.class || clazz == Float.class || clazz == Boolean.class ||
                clazz == Character.class || clazz == Byte.class;
    }

    /**
     * 判断对象是否非空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
