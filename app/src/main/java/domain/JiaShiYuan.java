package domain;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by raphael on 2017/5/22.
 */

public class JiaShiYuan extends DataSupport{

    private Integer id ;	//id
    private String jiashiyuanxingming;	//驾驶员姓名
    private String jiashicheliang;	//驾驶车辆
    private String jiashiyuanleixing;	//驾驶员类型
    private String bumen;	//部门
    private String chushengriqi;	//出生日期
    private String jiguan;	//籍贯
    private String shenfenzhenghao;	//身份证号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJiashiyuanxingming() {
        return jiashiyuanxingming;
    }

    public void setJiashiyuanxingming(String jiashiyuanxingming) {
        this.jiashiyuanxingming = jiashiyuanxingming;
    }

    public String getJiashicheliang() {
        return jiashicheliang;
    }

    public void setJiashicheliang(String jiashicheliang) {
        this.jiashicheliang = jiashicheliang;
    }

    public String getJiashiyuanleixing() {
        return jiashiyuanleixing;
    }

    public void setJiashiyuanleixing(String jiashiyuanleixing) {
        this.jiashiyuanleixing = jiashiyuanleixing;
    }

    public String getBumen() {
        return bumen;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }

    public String getChushengriqi() {
        return chushengriqi;
    }

    public void setChushengriqi(String chushengriqi) {
        this.chushengriqi = chushengriqi;
    }

    public String getJiguan() {
        return jiguan;
    }

    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }

    public String getShenfenzhenghao() {
        return shenfenzhenghao;
    }

    public void setShenfenzhenghao(String shenfenzhenghao) {
        this.shenfenzhenghao = shenfenzhenghao;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getZhunjiachexing() {
        return zhunjiachexing;
    }

    public void setZhunjiachexing(String zhunjiachexing) {
        this.zhunjiachexing = zhunjiachexing;
    }

    public String getChucilingzhengriqi() {
        return chucilingzhengriqi;
    }

    public void setChucilingzhengriqi(String chucilingzhengriqi) {
        this.chucilingzhengriqi = chucilingzhengriqi;
    }

    public String getJiashizhengnianshenriqi() {
        return jiashizhengnianshenriqi;
    }

    public void setJiashizhengnianshenriqi(String jiashizhengnianshenriqi) {
        this.jiashizhengnianshenriqi = jiashizhengnianshenriqi;
    }

    public String getDanganbianhao() {
        return danganbianhao;
    }

    public void setDanganbianhao(String danganbianhao) {
        this.danganbianhao = danganbianhao;
    }

    public String getCongyezigezhenghao() {
        return congyezigezhenghao;
    }

    public void setCongyezigezhenghao(String congyezigezhenghao) {
        this.congyezigezhenghao = congyezigezhenghao;
    }

    public String getCongyezigeleibie() {
        return congyezigeleibie;
    }

    public void setCongyezigeleibie(String congyezigeleibie) {
        this.congyezigeleibie = congyezigeleibie;
    }

    public String getCongyezigejixujiaoyushijian() {
        return congyezigejixujiaoyushijian;
    }

    public void setCongyezigejixujiaoyushijian(String congyezigejixujiaoyushijian) {
        this.congyezigejixujiaoyushijian = congyezigejixujiaoyushijian;
    }

    public String getCongyezigechengxinkaoheshijian() {
        return congyezigechengxinkaoheshijian;
    }

    public void setCongyezigechengxinkaoheshijian(String congyezigechengxinkaoheshijian) {
        this.congyezigechengxinkaoheshijian = congyezigechengxinkaoheshijian;
    }

    public String getZhengjianyouxiaoriqi() {
        return zhengjianyouxiaoriqi;
    }

    public void setZhengjianyouxiaoriqi(String zhengjianyouxiaoriqi) {
        this.zhengjianyouxiaoriqi = zhengjianyouxiaoriqi;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getJiashiyuantupian() {
        return jiashiyuantupian;
    }

    public void setJiashiyuantupian(String jiashiyuantupian) {
        this.jiashiyuantupian = jiashiyuantupian;
    }

    public String getJiashizhengtupian() {
        return jiashizhengtupian;
    }

    public void setJiashizhengtupian(String jiashizhengtupian) {
        this.jiashizhengtupian = jiashizhengtupian;
    }

    public String getCongyezigezhengtupian() {
        return congyezigezhengtupian;
    }

    public void setCongyezigezhengtupian(String congyezigezhengtupian) {
        this.congyezigezhengtupian = congyezigezhengtupian;
    }

    public String getShenfenzhengtupian() {
        return shenfenzhengtupian;
    }

    public void setShenfenzhengtupian(String shenfenzhengtupian) {
        this.shenfenzhengtupian = shenfenzhengtupian;
    }

    public String getCaozuoyuan() {
        return caozuoyuan;
    }

    public void setCaozuoyuan(String caozuoyuan) {
        this.caozuoyuan = caozuoyuan;
    }

    public String getCaozuoriqi() {
        return caozuoriqi;
    }

    public void setCaozuoriqi(String caozuoriqi) {
        this.caozuoriqi = caozuoriqi;
    }

    public String getShenhezhuangtai() {
        return shenhezhuangtai;
    }

    public void setShenhezhuangtai(String shenhezhuangtai) {
        this.shenhezhuangtai = shenhezhuangtai;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    private String dianhua;	//电话
    private String dizhi;	//地址
    private String zhunjiachexing;	//准驾车型
    private String chucilingzhengriqi;	//初次领证日期
    private String jiashizhengnianshenriqi;	//驾驶证年审日期
    private String danganbianhao;	//档案编号
    private String congyezigezhenghao;	//从业资格证号
    private String congyezigeleibie;	//从业资格类别
    private String congyezigejixujiaoyushijian;	//从业资格继续教育时间
    private String congyezigechengxinkaoheshijian;	//从业资格诚信考核时间
    private String zhengjianyouxiaoriqi;	//证件有效日期
    private String beizhu;	//备注
    private String jiashiyuantupian;	//驾驶员图片
    private String jiashizhengtupian;	//驾驶证图片
    private String congyezigezhengtupian;	//从业资格证图片
    private String shenfenzhengtupian;	//身份证图片
    private String caozuoyuan;	//操作员
    private String caozuoriqi;	//操作日期
    private String shenhezhuangtai;	//审核状态
    private String mima;	//密码

}
