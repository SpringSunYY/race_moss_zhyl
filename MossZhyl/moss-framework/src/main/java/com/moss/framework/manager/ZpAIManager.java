package com.moss.framework.manager;

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
