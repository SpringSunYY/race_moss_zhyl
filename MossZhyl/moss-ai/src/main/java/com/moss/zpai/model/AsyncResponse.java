package com.moss.zpai.model;

import com.moss.common.utils.StringUtils;
import com.moss.common.utils.spring.SpringUtils;
import lombok.Setter;

import java.util.List;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zpai.model
 * @Author: YY
 * @CreateTime: 2024-10-30  22:44
 * @Description: AsyncResponse
 * @Version: 1.0
 */
public class AsyncResponse {
    private String message;
    private Integer totalTokens;
    private String requestId;
    private String taskStatus;
    private long created;
    private String model;
    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(Integer totalTokens) {
        this.totalTokens = totalTokens;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AsyncResponse{" +
                "message='" + message + '\'' +
                ", totalTokens=" + totalTokens +
                ", requestId='" + requestId + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
