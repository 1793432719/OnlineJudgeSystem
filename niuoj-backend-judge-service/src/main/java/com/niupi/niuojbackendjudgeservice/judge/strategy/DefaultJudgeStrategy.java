package com.niupi.niuojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;
import com.niupi.niuojbackendmodel.model.dto.question.JudgeCase;
import com.niupi.niuojbackendmodel.model.dto.question.JudgeConfig;
import com.niupi.niuojbackendmodel.model.entity.Question;
import com.niupi.niuojbackendmodel.model.enums.JudgeInfoMessageEnum;


import java.util.List;

/**
 * 默认策略模式
 */
public class DefaultJudgeStrategy implements JudgeStrategy {

    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        long memoryLimit = judgeInfo.getMemoryLimit();
        long time = judgeInfo.getTime();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemoryLimit(memoryLimit);
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            return judgeInfoResponse;

        }
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                return judgeInfoResponse;

            }
        }
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        long needMemoryLimit = judgeConfig.getMemoryLimit();
        long needTimeLimit = judgeConfig.getTimeLimit();
        if (memoryLimit > needMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
        }
        if (needTimeLimit > time) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
        }
        return judgeInfoResponse;
    }
}
