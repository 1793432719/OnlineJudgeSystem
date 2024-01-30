package com.niupi.niuojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.niupi.niuojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.niupi.niuojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;
import com.niupi.niuojbackendmodel.model.entity.User;
import com.niupi.niuojbackendmodel.model.vo.QuestionSubmitVO;

/**
* @author Tom
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-12-26 20:36:29
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest , User loginUser);
    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionQueryRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
