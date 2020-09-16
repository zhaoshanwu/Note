package com.sw.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component  // 加入到Ioc容器
@ConfigurationProperties(prefix = "yaml.level")  // 读取配置中前缀为yaml下的数据
public class YmalEntity {
    private String star;
    private String SpecialStr;
    private int num;
    private double Dnum;
    private Date birth;
    private List<String> list;
    private Set set;
    private Map<String,String> map;
    private User user;

    @Override
    public String toString() {
        return "YmalEntity{" +
                "star='" + star + '\'' +
                ", SpecialStr='" + SpecialStr + '\'' +
                ", num=" + num +
                ", Dnum=" + Dnum +
                ", birth=" + birth +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", user=" + user +
                '}';
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getSpecialStr() {
        return SpecialStr;
    }

    public void setSpecialStr(String specialStr) {
        SpecialStr = specialStr;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getDnum() {
        return Dnum;
    }

    public void setDnum(double dnum) {
        Dnum = dnum;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
