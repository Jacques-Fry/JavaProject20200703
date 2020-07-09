package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "huihe56_user")
public class User implements Serializable {

  private String cunitname;
  private String username;
  private String password;

  @Id
  private Long id;

  private String secret;
  private String site;
  private String logintime;
  private String qqJson;
  private String qqBind;
  private String weixinBind;
  private String weixinJson;
  private String sinaJson;
  private String sinaBind;
  private String starttime;
  private Double fcredit;
  private Double amount;
  private Long cycle;
  private String weixinHeadimgurl;
  private String headimgurl;

  private String cphone;

  private long logonnum;
  private long agreementstatic;


  public String getCunitname() {
    return cunitname;
  }

  public void setCunitname(String cUnitName) {
    this.cunitname = cUnitName;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }


  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }


  public String getLogintime() {
    return logintime;
  }

  public void setLogintime(String logintime) {
    this.logintime = logintime;
  }


  public String getQqJson() {
    return qqJson;
  }

  public void setQqJson(String qqJson) {
    this.qqJson = qqJson;
  }


  public String getQqBind() {
    return qqBind;
  }

  public void setQqBind(String qqBind) {
    this.qqBind = qqBind;
  }


  public String getWeixinBind() {
    return weixinBind;
  }

  public void setWeixinBind(String weixinBind) {
    this.weixinBind = weixinBind;
  }


  public String getWeixinJson() {
    return weixinJson;
  }

  public void setWeixinJson(String weixinJson) {
    this.weixinJson = weixinJson;
  }


  public String getSinaJson() {
    return sinaJson;
  }

  public void setSinaJson(String sinaJson) {
    this.sinaJson = sinaJson;
  }


  public String getSinaBind() {
    return sinaBind;
  }

  public void setSinaBind(String sinaBind) {
    this.sinaBind = sinaBind;
  }


  public String getStarttime() {
    return starttime;
  }

  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }


  public Double getFCredit() {
    return fcredit;
  }

  public void setFCredit(Double fCredit) {
    this.fcredit = fCredit;
  }


  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }


  public Long getCycle() {
    return cycle;
  }

  public void setCycle(Long cycle) {
    this.cycle = cycle;
  }


  public String getWeixinHeadimgurl() {
    return weixinHeadimgurl;
  }

  public void setWeixinHeadimgurl(String weixinHeadimgurl) {
    this.weixinHeadimgurl = weixinHeadimgurl;
  }


  public String getHeadimgurl() {
    return headimgurl;
  }

  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
  }


  public String getCphone() {
    return cphone;
  }

  public void setCphone(String cPhone) {
    this.cphone = cPhone;
  }


  public long getLogonnum() {
    return logonnum;
  }

  public void setLogonnum(long logonnum) {
    this.logonnum = logonnum;
  }


  public long getAgreementstatic() {
    return agreementstatic;
  }

  public void setAgreementstatic(long agreementstatic) {
    this.agreementstatic = agreementstatic;
  }

}
