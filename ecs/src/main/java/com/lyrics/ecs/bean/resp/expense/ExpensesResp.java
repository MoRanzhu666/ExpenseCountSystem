package com.lyrics.ecs.bean.resp.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ExpensesResp {
    @JsonProperty("dailyTotal")
    private Map<String, Double> dailyTotal;

    @JsonProperty("monthlyTotal")
    private Map<String, Double> monthlyTotal;

    @JsonProperty("yearlyTotal")
    private Map<String, Double> yearlyTotal;

}
