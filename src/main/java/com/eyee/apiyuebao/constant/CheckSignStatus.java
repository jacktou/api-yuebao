package com.eyee.apiyuebao.constant;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Description:
 * Author:jack
 * Date:下午4:48 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public enum CheckSignStatus {
  NONE(-1,""),NORMAL(1,"正确") ,EXPIRE(2,"过期"),WRONG(3,"不符");

  private Integer value;
  private String fieldName;

  CheckSignStatus(Integer value,String fieldName){this.value=value;this.fieldName=fieldName;}
    public Integer getValue(){return this.value;}
    public String getFieldName(){return this.fieldName;}
  public static CheckSignStatus valueOf(Integer value){
      switch (value){
          case 1:
              return NORMAL;
          case 2:
              return EXPIRE;
          case 3:
              return WRONG;
              default:
                  return NONE;
      }

  }


}
