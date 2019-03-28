package wangsen.sxcz.viewpager_test.settingbutton;

/**
 * Created by 王森 on 2018/12/2.
 */
public class NetInfo {
    private String url;
    private String port;

    public NetInfo(String url, String port) {
        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
