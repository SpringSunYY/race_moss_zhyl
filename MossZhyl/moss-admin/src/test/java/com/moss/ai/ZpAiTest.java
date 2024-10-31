package com.moss.ai;

import com.moss.zpai.manager.ZpAIManager;
import com.moss.zpai.model.AsyncReturnData;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Project: MossZhyl
 * @Package: com.moss.test
 * @Author: YY
 * @CreateTime: 2024-10-30  09:04
 * @Description: ZpAiTest
 * @Version: 1.0
 */
@SpringBootTest
public class ZpAiTest {
    @Autowired
    private ZpAIManager zpAIManager;

    private static final String API_KEY = "69df9fa65a23796fb7fd885784eb2457.eHw4dWQU1Z1XtLDI";
    private static final ClientV4 client = new ClientV4.Builder(API_KEY)
            .networkConfig(60, 60, 60, 60, TimeUnit.SECONDS)
            .build();

    // 请自定义自己的业务id
    private static final String requestIdTemplate = "mycompany-%d";

    public static void main(String[] args) {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), "那我具体应该怎么操作");
        ChatMessage system = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        messages.add(chatMessage);
        messages.add(system);
        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("glm-4-plus")
                .stream(Boolean.FALSE)
                .invokeMethod(Constants.invokeMethodAsync)
                .messages(messages)
                .maxTokens(2048)
                .temperature(0.95F)
                .topP(0.7F)
                .requestId("20241030162518408f4bccf9a04c40")
                .build();
        ModelApiResponse invokeModelApiResp = client.invokeModelApi(chatCompletionRequest);


        System.out.println("invokeModelApiResp = " + invokeModelApiResp);
    }

    @Test
    public void testAI() {
        //建立sse连接对象 0永不超时
        SseEmitter sseEmitter = new SseEmitter(0L);
        Flowable<ModelData> modelDataFlowable = zpAIManager.doStreamRequest(systemMessage, userMessage, null);
        modelDataFlowable
                .observeOn(Schedulers.io())
                //获取内容
                .map(modelData -> modelData.getChoices().get(0).getDelta().getContent())
                .flatMap(message -> {
                    List<Character> characterList = new ArrayList<>();
                    for (char c : message.toCharArray()) {
                        characterList.add(c);
                    }
                    return Flowable.fromIterable(characterList);
                })
                .doOnNext(c -> {
                    System.out.println("c = " + c);
                })
                .doOnError((e) -> System.out.println("e = " + e))
                .doOnComplete(sseEmitter::complete)
                .subscribe();
    }

    @Test
    public void testAi2() {
        String s = zpAIManager.doSyncUnstableRequest(systemMessage, userMessage);
        System.out.println("s = " + s);
    }

    @Test
    public void testAsync() {
        AsyncReturnData asyncReturnData = zpAIManager.doAsyncStableRequest(systemMessage, userMessage);
        System.out.println("asyncReturnData = " + asyncReturnData);
    }

    static String userMessage = "{\n" +
            "  \"name\": \"YY\",\n" +
            "  \"sex\": \"男\",\n" +
            "  \"age\": 76,\n" +
            "  \"living_condition\": \"与配偶居住\",\n" +
            "  \"disability_status\": \"轻度\",\n" +
            "  \"disability_condition\": \"身体情况良好，不适合长跑，腿部残疾，坐轮椅,家住农村\",\n" +
            "  \"data\": \"2024-10-27\",\n" +
            "  \"avg\": {\n" +
            "    \"heartRate\": 20,\n" +
            "    \"dbp\": 60,\n" +
            "    \"sdp\": 90,\n" +
            "    \"oxygen\": 80,\n" +
            "    \"bloodSugar\": \"7\",\n" +
            "    \"str_temperature\": \"37\"\n" +
            "  },\n" +
            "  \"max\": {\n" +
            "    \"heartRate\": 24,\n" +
            "    \"dbp\": 65,\n" +
            "    \"sdp\": 95,\n" +
            "    \"oxygen\": 85,\n" +
            "    \"bloodSugar\": \"9\",\n" +
            "    \"str_temperature\": \"37.5\"\n" +
            "  },\n" +
            "  \"lower\": {\n" +
            "    \"heartRate\": 18,\n" +
            "    \"dbp\": 55,\n" +
            "    \"sdp\": 85,\n" +
            "    \"oxygen\": 75,\n" +
            "    \"bloodSugar\": \"9\",\n" +
            "    \"str_temperature\": \"36.8\"\n" +
            "  },\n" +
            "  \"mode\": {\n" +
            "    \"heartRate\": 20,\n" +
            "    \"dbp\": 59,\n" +
            "    \"sdp\": 81,\n" +
            "    \"oxygen\": 79,\n" +
            "    \"bloodSugar\": \"7\",\n" +
            "    \"str_temperature\": \"37\"\n" +
            "  }\n" +
            "}";
    static String systemMessage = "你是一个关于养老与养生的专家，深究智慧养老领域且认真负责，作为一个医生，我会你某一位老人的身体情况和一段时间数据，身体情况包括sex(性别)、age（年龄）、living_condition（居住情况）、disability_status（失能情况）、disability_condition（残疾情况）data(时间)，数据包括这段时间最大数据（max）、最低数据（lowe）、平均数据（avg），数据众数（mode）每种数据包括heartRate（心率）、dbp（低压）、sdp（高压）、oxygen（血氧）、bloodSugar（血糖）、str_temperature（温度）。你根据这些他的身体情况和数据认真且专业的分析数据在他这个年龄段是否正常，有什么潜在危险并给一些建议，建议在饮食饮食方面推荐吃什么食物，运动方面建议他做什么运动或者怎么调整身体，建议内容每种建议不少于两条且要根据他的身体情况做出更准确的建议，如果有较为严重的数据或者出现紧急情况，应该怎么做，并且总结生成一份健康报告，返回的内容为必须为Json，描述必须为中文。\n" +
            "以下是一个事例，你可以模仿事例内容根据实际情况做出分析，返回数据json格式必须和事例格式一样：\n" +
            "传入数据事例:\n" +
            "{\n" +
            "  \"name\": \"YY\",\n" +
            "  \"sex\": \"男\",\n" +
            "  \"age\": 76,\n" +
            "  \"living_condition\": \"与配偶居住\",\n" +
            "  \"disability_status\": \"轻度\",\n" +
            "  \"disability_condition\": \"身体情况良好，不适合长跑，腿部有旧伤\",\n" +
            "  \"data\": \"2024-10-27\",\n" +
            "  \"avg\": {\n" +
            "    \"heartRate\": 20,\n" +
            "    \"dbp\": 60,\n" +
            "    \"sdp\": 90,\n" +
            "    \"oxygen\": 80,\n" +
            "    \"bloodSugar\": \"7\",\n" +
            "    \"str_temperature\": \"37\"\n" +
            "  },\n" +
            "  \"max\": {\n" +
            "    \"heartRate\": 24,\n" +
            "    \"dbp\": 65,\n" +
            "    \"sdp\": 95,\n" +
            "    \"oxygen\": 85,\n" +
            "    \"bloodSugar\": \"9\",\n" +
            "    \"str_temperature\": \"37.5\"\n" +
            "  },\n" +
            "  \"lower\": {\n" +
            "    \"heartRate\": 18,\n" +
            "    \"dbp\": 55,\n" +
            "    \"sdp\": 85,\n" +
            "    \"oxygen\": 75,\n" +
            "    \"bloodSugar\": \"9\",\n" +
            "    \"str_temperature\": \"36.8\"\n" +
            "  },\n" +
            "  \"mode\": {\n" +
            "    \"heartRate\": 20,\n" +
            "    \"dbp\": 59,\n" +
            "    \"sdp\": 81,\n" +
            "    \"oxygen\": 79,\n" +
            "    \"bloodSugar\": \"7\",\n" +
            "    \"str_temperature\": \"37\"\n" +
            "  }\n" +
            "}\n" +
            "返回内容格式以及事例为：\n" +
            "{\n" +
            "  \"overallAssessment\": \"尊敬的{name}用户，{data}健康报告已经出炉，.......身体情况.....分析数据的总结\",\n" +
            "  \"detailedAnalysis\": {\n" +
            "    \"heartRate\": {\n" +
            "      \"currentStatus\": \"平均心率20次/分钟，最低18次/分钟，最高24次/分钟，在您这年龄阶段正常范围是多少是否属于正常范围。\",\n" +
            "      \"potentialRisks\": \"可能导致心脏供血不足，出现头晕、乏力、甚至晕厥。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"立即就医，进行心电图等检查，排除心脏疾病。\",\n" +
            "        \"避免剧烈运动，保持适当活动，如散步、太极拳等。\",\n" +
            "         \"........\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"bloodPressure\": {\n" +
            "      \"currentStatus\": \"平均低压60mmHg，高压90mmHg，最低低压55mmHg，最高高压95mmHg，在您这年龄阶段正常范围是多少是否属于正常范围。\",\n" +
            "      \"potentialRisks\": \"可能导致头晕、乏力，甚至晕厥。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"增加盐分摄入，但需在医生指导下进行。\",\n" +
            "        \"适量饮水，避免长时间站立。\",\n" +
            "        \"........\"  \n" +
            "      ]\n" +
            "    },\n" +
            "    \"oxygen\": {\n" +
            "      \"currentStatus\": \"平均血氧80%，最低75%，在您这年龄阶段正常范围是多少是否属于正常范围。\",\n" +
            "      \"potentialRisks\": \"可能导致组织缺氧，影响器官功能。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"保持室内空气流通，适当进行深呼吸练习。\",\n" +
            "         \"........\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"bloodSugar\": {\n" +
            "      \"currentStatus\": \"平均血糖7mmol/L，最高9mmol/L，最低9mmol/L，在您这年龄阶段正常范围是多少是否属于正常范围。\",\n" +
            "      \"potentialRisks\": \"可能导致糖尿病及其并发症。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"控制饮食，减少糖分摄入，选择低糖食物。\",\n" +
            "        \"定期监测血糖，必要时就医调整用药。\",\n" +
            "        \"........\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"temperature\": {\n" +
            "      \"currentStatus\": \"平均体温37℃，最高37.5℃，在您这年龄阶段正常范围是多少是否属于正常范围。\",\n" +
            "      \"potentialRisks\": \"无明显危险。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"保持正常作息，避免过度劳累。\",\n" +
            "        \"注意保暖，避免受凉。\",\n" +
            "        \"........\"  \n" +
            "      ]\n" +
            "    }\n" +
            "  },\n" +
            "  \"dietarySuggestions\": [\n" +
            "    \"增加富含钾的食物，如香蕉、橙子，有助于维持正常心率。\",\n" +
            "    \"选择低糖、高纤维的食物，如全麦面包、燕麦，控制血糖。\",\n" +
            "    \"适量摄入优质蛋白质，如鱼、鸡肉，增强体质。\",\n" +
            "    \"多吃新鲜蔬菜和水果，补充维生素和矿物质。\",\n" +
            "    \"........\"  \n" +
            "  ],\n" +
            "  \"exerciseSuggestions\": [\n" +
            "    \"每天进行30分钟左右的温和运动，如散步、慢跑。\",\n" +
            "    \"练习深呼吸和放松训练，有助于提高血氧水平。\",\n" +
            "    \"进行适量的力量训练，如哑铃操，增强肌肉力量。\",\n" +
            "    \"避免剧烈运动，以免加重心脏负担。\",\n" +
            "    \"........\"  \n" +
            "  ],\n" +
            "  \"emergencyHandling\": [\n" +
            "    \"若出现紧急情况自己无法做到，请联系相关服务人员（此条建议必须存在且必须为第一条）\"\n" +
            "    \"........\"  \n" +
            "  ]\n" +
            "}";
}
