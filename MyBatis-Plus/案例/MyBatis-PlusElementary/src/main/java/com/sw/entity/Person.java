package com.sw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 定义JavaBean中成员变量时所使用的类型：
 *      因为每个基本类型都有一个默认值
 *      比如 int ==> 0,boolean ==> false
 *      而包装类型有着一样的默认值---null，这样能够避免因为默认值带来的误解
 */

/**
 * MyBatis-Plus会默认使用实体类的类名去数据库中找对应的表
 */
@TableName(value = "person")
public class Person {
    /**
     * @TableId:
     *      value: 只当表中的主键列的列名，如果实体类属性名与列名一致，可以省略
     *      type： 指定主键策略
     */
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    private String name;
    private String sex;
    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
