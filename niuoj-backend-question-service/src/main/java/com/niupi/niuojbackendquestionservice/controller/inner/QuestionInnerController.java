package com.niupi.niuojbackendquestionservice.controller.inner;

import com.niupi.niuojbackendmodel.model.entity.Question;
import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;
import com.niupi.niuojbackendquestionservice.service.QuestionService;
import com.niupi.niuojbackendquestionservice.service.QuestionSubmitService;
import com.niupi.niuojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 内部服务调用，不暴露给前端调用
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {
    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }

    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @Override
    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }
}
