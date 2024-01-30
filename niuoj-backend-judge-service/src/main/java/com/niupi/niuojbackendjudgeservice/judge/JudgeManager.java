package com.niupi.niuojbackendjudgeservice.judge;

import com.niupi.niuojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.niupi.niuojbackendjudgeservice.judge.strategy.JavaLanguangeJudgeStrategy;
import com.niupi.niuojbackendjudgeservice.judge.strategy.JudgeContext;
import com.niupi.niuojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;
import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 简化对判题功能的调用，减少代码if-else判题调用不同的策略
 */
@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)){
            judgeStrategy = new JavaLanguangeJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
