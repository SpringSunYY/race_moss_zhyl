package com.moss.zpai.manager;

import com.moss.zpai.model.AsyncResponse;
import com.moss.zpai.model.AsyncReturnData;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: MossZhyl
 * @Package: com.moss.framework.manager
 * @Author: YY
 * @CreateTime: 2024-10-30  09:01
 * @Description: ZpAIManager
 * @Version: 1.0
 */
@Component
public class ZpAIManager {
    @Resource
    private ClientV4 clientV4;
    // 较稳定的随机数
    private static final float STABLE_TEMPERATURE = 0.05f;

    // 不稳定的随机数
    private static final float UNSTABLE_TEMPERATURE = 0.99f;

    //ai健康报告tokens
    private static final Integer AI_HEALTH_REPORT_TOKENS = 2048;
    private static final float AI_HEALTH_REPORT_TEMPERATURE = 0.7f;
    private static final String AI_HEALTH_REPORT_MODEL = "glm-4-plus";

    /**
     * @description: 获取异步返回结果
     * @author: YY
     * @method: getAsyncReturn
     * @date: 2024/10/30 22:47
     * @param:
     * @param: taskId
     * @return: com.moss.zpai.model.AsyncResponse
     **/
    public AsyncResponse getAsyncReturn(String taskId) {
        QueryModelResultRequest request = new QueryModelResultRequest();
        request.setTaskId(taskId);
        QueryModelResultResponse queryModelResultResponse = clientV4.queryModelResult(request);

        //构建返回参数
        ModelData responseData = queryModelResultResponse.getData();
        AsyncResponse asyncResponse = new AsyncResponse();
        asyncResponse.setTotalTokens(responseData.getUsage().getTotalTokens());
        asyncResponse.setRequestId(responseData.getRequestId());
        asyncResponse.setTaskStatus(responseData.getTaskStatus().toString());
        asyncResponse.setCreated(responseData.getCreated());
        asyncResponse.setModel(responseData.getModel());
        asyncResponse.setId(responseData.getId());
        asyncResponse.setMessage(responseData.getChoices().get(0).getMessage().getContent().toString());
        return asyncResponse;
    }

    /**
     * @description: 异步请求 稳定的
     * @author: YY
     * @method: doAsyncStableRequest
     * @date: 2024/10/30 21:34
     * @param:
     * @return: com.moss.zpai.model.AsyncReturnData
     **/
    public AsyncReturnData doAsyncStableRequest(String systemMessage, String userMessage) {
        // 构造请求
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemChatMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        ChatMessage userChatMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessage);
        messages.add(systemChatMessage);
        messages.add(userChatMessage);
        return doAsyncRequest(messages, AI_HEALTH_REPORT_TEMPERATURE, AI_HEALTH_REPORT_TOKENS);
    }

    /**
     * @description: 异步请求
     * @author: YY
     * @method: doAsyncRequest
     * @date: 2024/10/30 21:54
     * @param:
     * @param: messages
     * @param: temperature
     * @param: tokens
     * @return: com.moss.zpai.model.AsyncReturnData
     **/
    public AsyncReturnData doAsyncRequest(List<ChatMessage> messages, Float temperature, Integer tokens) {
        // 构造请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(AI_HEALTH_REPORT_MODEL)
                .invokeMethod(Constants.invokeMethodAsync)
                //必须要！！！
                .stream(false)
                .temperature(temperature)
                .messages(messages)
                .maxTokens(tokens)
                .build();
        ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);

        //构造返回数据
        ModelData data = invokeModelApiResp.getData();
        AsyncReturnData asyncReturnData = new AsyncReturnData();
        asyncReturnData.setId(data.getId());
        asyncReturnData.setRequestId(data.getRequestId());
        asyncReturnData.setModel(data.getModel());
        asyncReturnData.setTaskStatus(data.getTaskStatus().toString());

        return asyncReturnData;
    }

    /**
     * 同步调用（答案较稳定）
     *
     * @param systemMessage
     * @param userMessage
     * @return
     */
    public String doSyncStableRequest(String systemMessage, String userMessage) {
        return doSyncRequest(systemMessage, userMessage, STABLE_TEMPERATURE);
    }

    /**
     * 同步调用（答案较随机）
     *
     * @param systemMessage
     * @param userMessage
     * @return
     */
    public String doSyncUnstableRequest(String systemMessage, String userMessage) {
        return doSyncRequest(systemMessage, userMessage, UNSTABLE_TEMPERATURE);
    }


    public String doRequest(List<ChatMessage> messages, Boolean stream, Float temperature) {
        // 构造请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(stream)
                .invokeMethod(Constants.invokeMethod)
                .temperature(temperature)
                .messages(messages)
                .maxTokens(1024)
                .build();
        ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
        ChatMessage result = invokeModelApiResp.getData().getChoices().get(0).getMessage();
        return result.getContent().toString();
    }

    /**
     * @description: 流式请求
     * @author: YY
     * @method: doStreamRequest
     **/
    public Flowable<ModelData> doStreamRequest(List<ChatMessage> messages, Float temperature) {
        // 构造请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(Boolean.TRUE)
                .invokeMethod(Constants.invokeMethod)
                .temperature(temperature)
                .messages(messages)
                .build();
        ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
        return invokeModelApiResp.getFlowable();
    }

    /**
     * @description: 通用流式请求
     * @author: YY
     * @method: doStreamRequest
     **/
    public Flowable<ModelData> doStreamRequest(String systemMessage, String userMessage, Float temperature) {
        // 构造请求
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemChatMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        ChatMessage userChatMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessage);
        messages.add(systemChatMessage);
        messages.add(userChatMessage);
        return doStreamRequest(messages, temperature);
    }

    public String doRequest(String systemMessage, String userMessage, Boolean stream, Float temperature) {
        // 构造请求
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemChatMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        ChatMessage userChatMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessage);
        messages.add(systemChatMessage);
        messages.add(userChatMessage);
        return doRequest(messages, stream, temperature);
    }

    public String doSyncRequest(String systemMessage, String userMessage, Float temperature) {
        return doRequest(systemMessage, userMessage, Boolean.FALSE, temperature);
    }

}
