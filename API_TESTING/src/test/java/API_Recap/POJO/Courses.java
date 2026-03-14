package API_Recap.POJO;

import java.util.List;

public class Courses {
    private List<WebAutomation> webAutomation;
    private List<API> API;
    private List<Mobile> mobile;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<API> getAPI() {
        return API;
    }

    public void setAPI(List<API> API) {
        this.API = API;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

}
