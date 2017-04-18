package com.slli.netty.model;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/18.
 */
@Message
public class ShortMsg implements Serializable{
    private String name;
    private String sex;
    private String desc;
    private int age;
    private Date birth;

    public ShortMsg() {
    }

    public ShortMsg(String name, String sex, String desc, int age, Date birth) {
        this.name = name;
        this.sex = sex;
        this.desc = desc;
        this.age = age;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "ShortMsg{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", desc='" + desc + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }
}
