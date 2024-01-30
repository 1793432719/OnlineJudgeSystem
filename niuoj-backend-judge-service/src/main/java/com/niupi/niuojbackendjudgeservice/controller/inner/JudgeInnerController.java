package com.niupi.niuojbackendjudgeservice.controller.inner;

import com.niupi.niuojbackendjudgeservice.judge.JudgeService;
import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;
import com.niupi.niuojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 内部服务调用，不暴露给前端调用
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient{
    @Resource
    private JudgeService judgeService;

    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId){
        return judgeService.doJudge(questionSubmitId);
    }
}
