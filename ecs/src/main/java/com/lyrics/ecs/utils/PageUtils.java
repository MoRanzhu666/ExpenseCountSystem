package com.lyrics.ecs.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PageUtils {

    /**
     * 将 IPage<Source> 转换成 IPage<Target>
     * @param sourcePage 原始分页数据
     * @param targetClass 目标类型
     * @param afterCopyConsumer 每条记录拷贝后额外处理的逻辑（可选）
     * @return 转换后的 IPage<Target>
     */
    public static <S, T> IPage<T> convertPage(IPage<S> sourcePage, Class<T> targetClass, Consumer<T> afterCopyConsumer) {
        // 创建新的分页对象
        IPage<T> targetPage = new Page<>(sourcePage.getCurrent(), sourcePage.getSize(), sourcePage.getTotal());

        List<T> targetRecords = sourcePage.getRecords().stream().map(record -> {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(record, target);
                if (afterCopyConsumer != null) {
                    afterCopyConsumer.accept(target);
                }
                return target;
            } catch (Exception e) {
                throw new RuntimeException("转换失败", e);
            }
        }).collect(Collectors.toList());

        targetPage.setRecords(targetRecords);
        return targetPage;
    }

    public static <S, T> IPage<T> convertPage(IPage<S> sourcePage, Class<T> targetClass) {
        // 创建新的分页对象
        IPage<T> targetPage = new Page<>(sourcePage.getCurrent(), sourcePage.getSize(), sourcePage.getTotal());

        List<T> targetRecords = sourcePage.getRecords().stream().map(record -> {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(record, target);
                return target;
            } catch (Exception e) {
                throw new RuntimeException("转换失败", e);
            }
        }).collect(Collectors.toList());

        targetPage.setRecords(targetRecords);
        return targetPage;
    }
}
