package domain;

import org.litepal.crud.DataSupport;

/**
 * Created by raphael on 2017/5/24.
 */

public class IpAddress extends DataSupport{

    private String ip_address;

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getIp_port() {
        return ip_port;
    }

    public void setIp_port(String ip_port) {
        this.ip_port = ip_port;
    }

    private String ip_port;
}
