package com.lyrics.ecs.bean.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义分页类，封装分页查询的默认参数
 * 类似 MyBatis-Plus 的 IPage，包含页码、每页条数、总记录数等核心信息
 */
public class CustomIPage<T> implements IPage<T> {
    protected static final long serialVersionUID = 8545996863226528298L;
    @JsonIgnore
    protected String namespace;
    @JsonIgnore
    protected String tenantId;
    @JsonIgnore
    protected String sourceId;
    @JsonIgnore
    protected boolean admin;
    @JsonIgnore
    protected List<OrderItem> orders;
    @JsonIgnore
    protected boolean optimizeCountSql;
    @JsonIgnore
    protected boolean searchCount;
    @JsonIgnore
    protected boolean hitCount;
    @JsonIgnore
    protected String countId;
    @JsonIgnore
    protected Long maxLimit;
    protected List<T> records;
    protected long total;
    protected @Min(
            value = 1L,
            message = "每页展示数据不低于1条。"
    ) @Max(
            value = 5000L,
            message = "每页展示数据不得超过5000条。"
    ) long size;
    protected @Min(
            value = 1L,
            message = "当前页不能小于第1页。"
    ) long current;
    protected List<String> pagerTabs;

    public CustomIPage() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList();
        this.optimizeCountSql = true;
        this.searchCount = true;
        this.hitCount = false;
    }

    public CustomIPage(long current, long size) {
        this(current, size, 0L);
    }

    public CustomIPage(long current, long size, long total) {
        this(current, size, total, true);
    }

    public CustomIPage(long current, long size, boolean isSearchCount) {
        this(current, size, 0L, isSearchCount);
    }

    public CustomIPage(long current, long size, long total, boolean isSearchCount) {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList();
        this.optimizeCountSql = true;
        this.searchCount = true;
        this.hitCount = false;
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
        this.searchCount = isSearchCount;
    }

    public boolean hasPrevious() {
        return this.current > 1L;
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }

    public List<T> getRecords() {
        return this.records;
    }

    public CustomIPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public CustomIPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public CustomIPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return this.current;
    }

    public CustomIPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public String countId() {
        return this.getCountId();
    }

    public Long maxLimit() {
        return this.getMaxLimit();
    }

    private String[] mapOrderToArray(Predicate<OrderItem> filter) {
        List<String> columns = new ArrayList(this.orders.size());
        this.orders.forEach((i) -> {
            if (filter.test(i)) {
                columns.add(i.getColumn());
            }

        });
        return (String[])columns.toArray(new String[0]);
    }

    private void removeOrder(Predicate<OrderItem> filter) {
        for(int i = this.orders.size() - 1; i >= 0; --i) {
            if (filter.test(this.orders.get(i))) {
                this.orders.remove(i);
            }
        }

    }

    public CustomIPage<T> addOrder(OrderItem... items) {
        this.orders.addAll(Arrays.asList(items));
        return this;
    }

    public CustomIPage<T> addOrder(List<OrderItem> items) {
        this.orders.addAll(items);
        return this;
    }

    public List<OrderItem> orders() {
        return this.getOrders();
    }

    public boolean optimizeCountSql() {
        return this.optimizeCountSql;
    }

    public boolean isOptimizeCountSql() {
        return this.optimizeCountSql();
    }

    public boolean isSearchCount() {
        return this.searchCount;
    }

    public CustomIPage<T> setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    public CustomIPage<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

    public void hitCount(boolean hit) {
        this.hitCount = hit;
    }

    public void setHitCount(boolean hit) {
        this.hitCount = hit;
    }

    public boolean isHitCount() {
        return this.hitCount;
    }

    public List<OrderItem> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    public String getCountId() {
        return this.countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }

    public Long getMaxLimit() {
        return this.maxLimit;
    }

    public void setMaxLimit(Long maxLimit) {
        this.maxLimit = maxLimit;
    }

    public static long getSerialVersionUID() {
        return 8545996863226528298L;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public List<String> getPagerTabs() {
        return this.pagerTabs;
    }

    public void setPagerTabs(List<String> pagerTabs) {
        this.pagerTabs = pagerTabs;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
