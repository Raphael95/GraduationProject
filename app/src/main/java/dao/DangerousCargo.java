package dao;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by raphael on 2017/5/8.
 */

public class DangerousCargo extends DataSupport implements Serializable{

    private Integer dangerousId;
    private String cargoName;
    private String safeCardPath;

    public Integer getDangerousId() {
        return dangerousId;
    }

    public void setDangerousId(Integer dangerousId) {
        this.dangerousId = dangerousId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getSafeCardPath() {
        return safeCardPath;
    }

    public void setSafeCardPath(String safeCardPath) {
        this.safeCardPath = safeCardPath;
    }


}
