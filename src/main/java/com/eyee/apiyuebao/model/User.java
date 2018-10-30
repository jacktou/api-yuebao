package com.eyee.apiyuebao.model;

/**
 * Description:
 * Author:jack
 * Date:下午6:30 2018/10/30
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class User {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static Builder builder(){ return new Builder();}
    public static class Builder{

        private String id;
        private String name;

        public Builder id(String id){
            this.id=id;
            return this;
        }

        public Builder name(String name){
            this.name=name;
            return this;
        }
        public User bulid(){

            User user=new User();
            user.setId(id);
            user.setName(name);
            return user;

        }

    }
}
