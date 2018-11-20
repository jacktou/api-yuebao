package com.eyee.apiyuebao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Description:
 * Author:jack
 * Date:下午3:07 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaReq extends RequestBase{
   @NotBlank
    private String mobile;
}
