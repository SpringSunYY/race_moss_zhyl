package com.moss.ai;

import com.moss.common.utils.StringUtils;
import com.moss.zpai.manager.ZpAIManager;
import com.moss.zpai.model.AsyncResponse;
import com.zhipu.oapi.ClientV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Project: MossZhyl
 * @Package: com.moss.test
 * @Author: YY
 * @CreateTime: 2024-10-30  22:18
 * @Description: ZpAiAsyncTest
 * @Version: 1.0
 */
@SpringBootTest
public class ZpAiAsyncTest {
    private static final String API_KEY = "69df9fa65a23796fb7fd885784eb2457.eHw4dWQU1Z1XtLDI";
    private static final ClientV4 client = new ClientV4.Builder(API_KEY)
            .networkConfig(60, 60, 60, 60, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) {
        System.out.println(StringUtils.getCharacterBetween("{", "}", s));
    }

    static String s=
            "{\n" +
            "  \"overallAssessment\": \"尊敬的YY用户，2024-10-27健康报告已经出炉，根据您的身体情况和数据，您的心率、血压、血氧、血糖和体温均存在一些异常情况，需要引起重视。您的腿部残疾和居住在农村的情况也需特别考虑。以下是对您各项数据的详细分析和建议。\",\n" +
            "  \"detailedAnalysis\": {\n" +
            "    \"heartRate\": {\n" +
            "      \"currentStatus\": \"平均心率20次/分钟，最低18次/分钟，最高24次/分钟，低于正常范围（60-100次/分钟）。\",\n" +
            "      \"potentialRisks\": \"可能导致心脏供血不足，出现头晕、乏力、甚至晕厥。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"立即就医，进行心电图等检查，排除心脏疾病。\",\n" +
            "        \"避免剧烈运动，保持适当活动，如轮椅上的手臂运动、太极拳等。\",\n" +
            "        \"保持心情愉快，避免过度紧张和焦虑。\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"bloodPressure\": {\n" +
            "      \"currentStatus\": \"平均低压60mmHg，高压90mmHg，最低低压55mmHg，最高高压95mmHg，低压偏低，高压正常。\",\n" +
            "      \"potentialRisks\": \"可能导致头晕、乏力，甚至晕厥。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"增加盐分摄入，但需在医生指导下进行。\",\n" +
            "        \"适量饮水，避免长时间站立。\",\n" +
            "        \"定期监测血压，及时调整用药。\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"oxygen\": {\n" +
            "      \"currentStatus\": \"平均血氧80%，最低75%，低于正常范围（95%-100%）。\",\n" +
            "      \"potentialRisks\": \"可能导致组织缺氧，影响器官功能。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"保持室内空气流通，适当进行深呼吸练习。\",\n" +
            "        \"定期进行血氧监测，必要时使用氧疗设备。\",\n" +
            "        \"避免长时间保持同一姿势，适当活动身体。\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"bloodSugar\": {\n" +
            "      \"currentStatus\": \"平均血糖7mmol/L，最高9mmol/L，最低9mmol/L，接近或超过正常范围（3.9-6.1mmol/L）。\",\n" +
            "      \"potentialRisks\": \"可能导致糖尿病及其并发症。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"控制饮食，减少糖分摄入，选择低糖食物。\",\n" +
            "        \"定期监测血糖，必要时就医调整用药。\",\n" +
            "        \"增加膳食纤维摄入，如蔬菜、全谷物等。\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"temperature\": {\n" +
            "      \"currentStatus\": \"平均体温37℃，最高37.5℃，在正常范围（36.1-37.2℃）内，但偏高。\",\n" +
            "      \"potentialRisks\": \"可能存在轻度感染或炎症。\",\n" +
            "      \"suggestions\": [\n" +
            "        \"保持正常作息，避免过度劳累。\",\n" +
            "        \"注意保暖，避免受凉。\",\n" +
            "        \"多喝水，保持充足的水分摄入。\"\n" +
            "      ]\n" +
            "    }\n" +
            "  },\n" +
            "  \"dietarySuggestions\": [\n" +
            "    \"增加富含钾的食物，如香蕉、橙子，有助于维持正常心率。\",\n" +
            "    \"选择低糖、高纤维的食物，如全麦面包、燕麦，控制血糖。\",\n" +
            "    \"适量摄入优质蛋白质，如鱼、鸡肉，增强体质。\",\n" +
            "    \"多吃新鲜蔬菜和水果，补充维生素和矿物质。\",\n" +
            "    \"避免高脂肪、高盐食物，减少心血管负担。\"\n" +
            "  ],\n" +
            "  \"exerciseSuggestions\": [\n" +
            "    \"每天进行30分钟左右的温和运动，如轮椅上的手臂运动、太极拳。\",\n" +
            "    \"练习深呼吸和放松训练，有助于提高血氧水平。\",\n" +
            "    \"进行适量的力量训练，如哑铃操，增强肌肉力量。\",\n" +
            "    \"避免剧烈运动，以免加重心脏负担。\",\n" +
            "    \"定期进行腿部按摩，促进血液循环。\"\n" +
            "  ],\n" +
            "  \"emergencyHandling\": [\n" +
            "    \"若出现紧急情况自己无法做到，请联系相关服务人员。\",\n" +
            "    \"出现头晕、乏力等症状时，立即休息并测量血压、心率。\",\n" +
            "    \"若血氧低于90%，立即使用氧疗设备并联系医生。\",\n" +
            "    \"血糖过高或过低时，及时补充食物或药物，并就医。\",\n" +
            "    \"体温持续升高，超过38℃时，立即就医。\"\n" +
            "  ]\n" +
            "}";

    @Autowired
    private ZpAIManager zpAIManager;

    @Test
    public void getAsync() {
        AsyncResponse asyncReturn = zpAIManager.getAsyncReturn("177017226898422989157395223576135782");
        System.out.println(asyncReturn.getMessage().length());
        asyncReturn.setMessage(StringUtils.getCharacterBetween("{", "}", asyncReturn.getMessage()));
        System.out.println("asyncReturn = " + asyncReturn);
    }
}
