package com.moss.zpai.model;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zpai.model
 * @Author: YY
 * @CreateTime: 2024-10-30  21:31
 * @Description: AsyncReturnData
 * 异步请求返回结果
 * @Version: 1.0
 */
public class AsyncReturnData {
    private String id;
    private String requestId;
    private String model;
    private String taskStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "AsyncReturnData{" +
                "id='" + id + '\'' +
                ", requestId='" + requestId + '\'' +
                ", model='" + model + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
    }
}
