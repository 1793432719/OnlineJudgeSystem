package com.niupi.niuojbackendjudgeservice.judge.strategy;

import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;
import com.niupi.niuojbackendmodel.model.dto.question.JudgeCase;
import com.niupi.niuojbackendmodel.model.entity.Question;
import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 用于定义在策略中传递的参数
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}
