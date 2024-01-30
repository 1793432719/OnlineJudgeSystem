package com.niupi.niuojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * 題目用例
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;
    /**
     * 输出用例
     */
    private String output;
}
