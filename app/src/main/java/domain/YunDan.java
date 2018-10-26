package domain;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by raphael on 2017/5/23.
 */

public class YunDan extends DataSupport implements Serializable{
    private Integer id;	//id
    private String dingdanhao;	//订单号
    private String chepaihao;	//车牌号
    private String kehudanwei;	//客户单位
    private String qidian;	//起点

    private String shifadunwei;	//实发吨位
    private String shishoudunwei;	//实收吨位

    public String getShifadunwei() {
        return shifadunwei;
    }

    public void setShifadunwei(String shifadunwei) {
        this.shifadunwei = shifadunwei;
    }

    public String getShishoudunwei() {
        return shishoudunwei;
    }

    public void setShishoudunwei(String shishoudunwei) {
        this.shishoudunwei = shishoudunwei;
    }

    public String getLicheng() {
        return licheng;
    }

    public void setLicheng(String licheng) {
        this.licheng = licheng;
    }

    public String getDaidianfei() {
        return daidianfei;
    }

    public void setDaidianfei(String daidianfei) {
        this.daidianfei = daidianfei;
    }

    public String getFeiyongmiaoshu() {
        return feiyongmiaoshu;
    }

    public void setFeiyongmiaoshu(String feiyongmiaoshu) {
        this.feiyongmiaoshu = feiyongmiaoshu;
    }

    private String licheng;	//里程
    private String daidianfei;	//代垫费
    private String feiyongmiaoshu;	//费用描述


    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    private String dianhua;   //电话号码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDingdanhao() {
        return dingdanhao;
    }

    public void setDingdanhao(String dingdanhao) {
        this.dingdanhao = dingdanhao;
    }

    public String getChepaihao() {
        return chepaihao;
    }

    public void setChepaihao(String chepaihao) {
        this.chepaihao = chepaihao;
    }

    public String getKehudanwei() {
        return kehudanwei;
    }

    public void setKehudanwei(String kehudanwei) {
        this.kehudanwei = kehudanwei;
    }

    public String getQidian() {
        return qidian;
    }

    public void setQidian(String qidian) {
        this.qidian = qidian;
    }

    public String getZhongdian() {
        return zhongdian;
    }

    public void setZhongdian(String zhongdian) {
        this.zhongdian = zhongdian;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getLianxiren() {
        return lianxiren;
    }

    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren;
    }

    public String getBaochouleixing() {
        return baochouleixing;
    }

    public void setBaochouleixing(String baochouleixing) {
        this.baochouleixing = baochouleixing;
    }

    public String getBaochoujine() {
        return baochoujine;
    }

    public void setBaochoujine(String baochoujine) {
        this.baochoujine = baochoujine;
    }

    public String getHuowumingcheng() {
        return huowumingcheng;
    }

    public void setHuowumingcheng(String huowumingcheng) {
        this.huowumingcheng = huowumingcheng;
    }

    public String getYujidunwei() {
        return yujidunwei;
    }

    public void setYujidunwei(String yujidunwei) {
        this.yujidunwei = yujidunwei;
    }

    public String getAnquanka() {
        return anquanka;
    }

    public void setAnquanka(String anquanka) {
        this.anquanka = anquanka;
    }

    public String getYunshushijian() {
        return yunshushijian;
    }

    public void setYunshushijian(String yunshushijian) {
        this.yunshushijian = yunshushijian;
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

    public String getYundanzhuangtai() {
        return yundanzhuangtai;
    }

    public void setYundanzhuangtai(String yundanzhuangtai) {
        this.yundanzhuangtai = yundanzhuangtai;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    private String zhongdian;	//终点
    private String dizhi;	//地址
    private String lianxiren;	//联系人
    private String baochouleixing;	//报酬类型
    private String baochoujine;	//报酬金额
    private String huowumingcheng;	//货物名称
    private String yujidunwei;	//预计吨位
    private String anquanka;	//安全卡
    private String yunshushijian;	//运输时间
    private String caozuoyuan;	//操作员
    private String caozuoriqi;	//操作日期

    public String getAnquankapath() {
        return anquankapath;
    }

    public void setAnquankapath(String anquankapath) {
        this.anquankapath = anquankapath;
    }

    private String yundanzhuangtai;	//运单状态
    private String beizhu;	//备注
    private String anquankapath;

}
