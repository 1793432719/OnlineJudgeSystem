package com.niupi.niuojbackendjudgeservice.judge;


import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
