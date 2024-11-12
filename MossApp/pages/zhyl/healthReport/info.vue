<template>
  <view class="container">
    <view class="header">
      <!--      <text class="title">健康报告详情</text>-->
      <text class="summary content">{{ overallAssessment }}</text>
    </view>

    <uni-collapse>
      <uni-collapse-item title="心率" name="heartRate">
        <view class="content">{{ detailedAnalysis.heartRate.currentStatus }}</view>
        <view class="risk">风险: {{ detailedAnalysis.heartRate.potentialRisks }}</view>
        <view class="suggestions">建议:</view>
        <ul>
          <li v-for="(suggestion, index) in detailedAnalysis.heartRate.suggestions" :key="index">
            {{ suggestion }}
          </li>
        </ul>
      </uni-collapse-item>

      <uni-collapse-item title="血压" name="bloodPressure">
        <view class="content">{{ detailedAnalysis.bloodPressure.currentStatus }}</view>
        <view class="risk">风险: {{ detailedAnalysis.bloodPressure.potentialRisks }}</view>
        <view class="suggestions">建议:</view>
        <ul>
          <li v-for="(suggestion, index) in detailedAnalysis.bloodPressure.suggestions" :key="index">
            {{ suggestion }}
          </li>
        </ul>
      </uni-collapse-item>

      <uni-collapse-item title="血氧" name="oxygen">
        <view class="content">{{ detailedAnalysis.oxygen.currentStatus }}</view>
        <view class="risk">风险: {{ detailedAnalysis.oxygen.potentialRisks }}</view>
        <view class="suggestions">建议:</view>
        <ul>
          <li v-for="(suggestion, index) in detailedAnalysis.oxygen.suggestions" :key="index">
            {{ suggestion }}
          </li>
        </ul>
      </uni-collapse-item>

      <uni-collapse-item title="血糖" name="bloodSugar">
        <view class="content">{{ detailedAnalysis.bloodSugar.currentStatus }}</view>
        <view class="risk">风险: {{ detailedAnalysis.bloodSugar.potentialRisks }}</view>
        <view class="suggestions">建议:</view>
        <ul>
          <li v-for="(suggestion, index) in detailedAnalysis.bloodSugar.suggestions" :key="index">
            {{ suggestion }}
          </li>
        </ul>
      </uni-collapse-item>

      <uni-collapse-item title="体温" name="temperature">
        <view class="content">{{ detailedAnalysis.temperature.currentStatus }}</view>
        <view class="risk">风险: {{ detailedAnalysis.temperature.potentialRisks }}</view>
        <view class="suggestions">建议:</view>
        <ul>
          <li v-for="(suggestion, index) in detailedAnalysis.temperature.suggestions" :key="index">
            {{ suggestion }}
          </li>
        </ul>
      </uni-collapse-item>
    </uni-collapse>

    <view class="section">
      <text class="section-title">饮食建议</text>
      <ul>
        <li v-for="(item, index) in dietarySuggestions" :key="index">
          {{ item }}
        </li>
      </ul>
    </view>

    <view class="section">
      <text class="section-title">运动建议</text>
      <ul>
        <li v-for="(item, index) in exerciseSuggestions" :key="index">
          {{ item }}
        </li>
      </ul>
    </view>

    <view class="section">
      <text class="section-title">紧急处理</text>
      <ul>
        <li v-for="(item, index) in emergencyHandling" :key="index">
          {{ item }}
        </li>
      </ul>
    </view>
  </view>
</template>

<script>
import {getHealthReport} from "@/api/zhyl/healthReport"

export default {
  data() {
    return {
      healthReportId: 0,
      overallAssessment: "尊敬的ai报告测试用户，2024-10-29至2024-10-31的健康报告已经出炉。您的整体健康状况需要关注，尤其是心率、血压和血糖方面存在一些潜在风险。由于您腿部残疾且智力有一定问题，建议在日常生活中特别注意安全和健康管理的细节。",
      detailedAnalysis: {
        heartRate: {
          currentStatus: "平均心率53次/分钟，最低0次/分钟（数据异常），最高80次/分钟。在您这年龄阶段，正常心率范围应为60-100次/分钟，您的平均心率偏低，最高心率偏高。",
          potentialRisks: "可能导致心脏供血不足，出现头晕、乏力、甚至晕厥。",
          suggestions: [
            // "立即就医，进行心电图等检查，排除心脏疾病。",
            // "避免剧烈运动，保持适当活动，如散步、太极拳等。",
            // "定期监测心率，注意心率变化。"
          ]
        },
        bloodPressure: {
          currentStatus: "平均低压62mmHg，高压91mmHg，最低低压0mmHg（数据异常），最高高压95mmHg。在您这年龄阶段，正常血压范围应为90/60-140/90mmHg，您的低压偏低，高压接近上限。",
          potentialRisks: "可能导致头晕、乏力，甚至晕厥。",
          suggestions: [
            // "增加盐分摄入，但需在医生指导下进行。",
            // "适量饮水，避免长时间站立。",
            // "定期监测血压，注意血压变化。"
          ]
        },
        oxygen: {
          currentStatus: "平均血氧89%，最低0%（数据异常），最高99%。在您这年龄阶段，正常血氧饱和度应大于95%，您的平均血氧偏低。",
          potentialRisks: "可能导致组织缺氧，影响器官功能。",
          suggestions: [
            // "保持室内空气流通，适当进行深呼吸练习。",
            // "定期进行血氧监测，注意血氧变化。",
            // "避免长时间卧床，适当活动以促进血液循环。"
          ]
        },
        bloodSugar: {
          currentStatus: "平均血糖6.739mmol/L，最高7.0mmol/L，最低0.0mmol/L（数据异常）。在您这年龄阶段，正常空腹血糖范围应为3.9-6.1mmol/L，您的平均血糖偏高。",
          potentialRisks: "可能导致糖尿病及其并发症。",
          suggestions: [
            // "控制饮食，减少糖分摄入，选择低糖食物。",
            // "定期监测血糖，必要时就医调整用药。",
            // "增加膳食纤维摄入，如全麦面包、燕麦等。"
          ]
        },
        temperature: {
          currentStatus: "平均体温37.078℃，最高37.3℃，最低0.0℃（数据异常）。在您这年龄阶段，正常体温范围应为36.1-37.2℃，您的体温略高。",
          potentialRisks: "可能存在轻度发热或炎症。",
          suggestions: [
            // "保持正常作息，避免过度劳累。",
            // "注意保暖，避免受凉。",
            // "定期监测体温，如有持续发热应及时就医。"
          ]
        }
      },
      dietarySuggestions: [
        // "增加富含钾的食物，如香蕉、橙子，有助于维持正常心率。",
        // "选择低糖、高纤维的食物，如全麦面包、燕麦，控制血糖。",
        // "适量摄入优质蛋白质，如鱼、鸡肉，增强体质。",
        // "多吃新鲜蔬菜和水果，补充维生素和矿物质。",
        // "避免高脂肪、高盐食物，以降低血压负担。"
      ],
      exerciseSuggestions: [
        // "每天进行30分钟左右的温和运动，如散步、慢跑。",
        // "练习深呼吸和放松训练，有助于提高血氧水平。",
        // "进行适量的力量训练，如哑铃操，增强肌肉力量。",
        // "避免剧烈运动，以免加重心脏负担。",
        // "根据身体状况，适当进行水中运动，如游泳，减少腿部压力。"
      ],
      emergencyHandling: [
        // "若出现紧急情况自己无法做到，请联系相关服务人员。",
        // "如出现严重头晕、乏力或晕厥，立即拨打急救电话。",
        // "如心率、血压、血氧等指标出现异常波动，及时就医。",
        // "保持家中常备急救药品，如硝酸甘油等。",
        // "定期进行健康检查，及时发现和处理潜在问题。"
      ]
    };
  },
  onLoad(option) {
    this.healthReportId = option.healthReportId
    this.getHealthReportData()
  },
  methods: {
    getHealthReportData() {
      getHealthReport(this.healthReportId).then(res=>{
        const data = res.data.reportReturnData
        this.overallAssessment = data.overallAssessment
        this.detailedAnalysis = data.detailedAnalysis
        this.dietarySuggestions = data.dietarySuggestions
        this.exerciseSuggestions = data.exerciseSuggestions
        this.emergencyHandling = data.emergencyHandling
      })
    }
  },
};
</script>

<style scoped>
.container {
  padding: 16px;
}

.header {
  text-indent: 2em; /* 首行缩进 */
  margin-bottom: 16px;
}

.title {
  font-size: 20px;
  font-weight: bold;
}

.summary {
  margin-top: 8px;
  font-size: 14px;
  color: #666;
}

.content {
  text-indent: 2em; /* 首行缩进 */
}

.uni-collapse-item {
  margin-bottom: 10px;
}

.risk {
  font-weight: bold;
  color: red;
}

.suggestions {
  font-weight: bold;
  margin-top: 8px;
}

.section {
  margin-top: 16px;
}

.section-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
}
</style>
