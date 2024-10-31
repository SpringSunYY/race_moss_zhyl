package com.moss.ai;

import com.moss.common.utils.StringUtils;
import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.DeviceUploadingData.ElderlyHealthData;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.zpai.manager.ZpAIManager;
import com.moss.zpai.model.AsyncResponse;
import com.moss.zpai.model.AsyncReturnData;
import com.zhipu.oapi.ClientV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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

    static String s =
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

    @Autowired
    private IDeviceUploadingDataService deviceUploadingDataService;

    @Test
    public void testAsync() {
        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
        deviceUploadingData.setUserInfoId(1849308235669049352L);
        deviceUploadingData.setCommand("health");
        deviceUploadingData.setCreateTime(new Date());
        ElderlyHealthData elderlyHealthData = deviceUploadingDataService.getElderlyHealthData(deviceUploadingData);
        String userMessage = elderlyHealthData.toString();
        AsyncReturnData asyncReturnData = zpAIManager.doAsyncStableRequest(systemMessage, userMessage);
        System.out.println("asyncReturnData = " + asyncReturnData);
    }

    @Test
    public void getAsync() {
        AsyncResponse asyncReturn = zpAIManager.getAsyncReturn("177017226898422989157399724703463014");
        System.out.println(asyncReturn.getMessage().length());
        System.out.println(asyncReturn.getMessage());
        asyncReturn.setMessage(StringUtils.getCharacterBetween("{", "}", asyncReturn.getMessage()));
        System.out.println("asyncReturn = " + asyncReturn);
    }

    static String systemMessage = "你是一个关于养老与养生的专家，深究智慧养老领域且认真负责，作为一个医生，我会你某一位老人的身体情况和一段时间的身体健康数据，身体情况包括sex(性别)、age（年龄）、living_condition（居住情况）、disability_status（失能情况）、disability_condition（残疾情况）data(时间)，数据包括这段时间最大数据（max）、最低数据（lowe）、平均数据（avg），数据众数（mode）每种数据包括heartRate（心率）、dbp（低压）、sdp（高压）、oxygen（血氧）、bloodSugar（血糖）、str_temperature（温度）。你根据这些他的身体情况和数据认真且专业的分析数据在他这个年龄段是否正常，有什么潜在危险并给一些建议，建议在饮食方面推荐吃什么食物，运动方面根据他的身体情况和身体健康数据如何运动，建议内容每种建议不少于两条且要根据他的身体情况做出更准确的建议，如果有较为严重的数据或者出现紧急情况，应该怎么做，并且总结生成一份健康报告，返回的内容为必须为Json，描述必须为中文。\n" +
            "以下是一个事例，你可以模仿事例内容根据实际情况做出分析，返回数据json格式必须和事例格式一样不能有一点的改变，否则会出现意想不到的问题，传入数据事例:\n" +
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
