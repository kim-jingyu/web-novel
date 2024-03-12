package com.webnovel.global.detector;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class LoggingForm {
    @Setter
    private String routerUrl;
    @Setter
    private String routerMethod;
    private int queryCount = 0;
    private Long queryTime = 0L;

    public void queryCountUp() {
        queryCount++;
    }

    public void addQueryTime(Long queryTime) {
        this.queryTime += queryTime;
    }
}
