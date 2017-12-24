package autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ConfigurationProperties(prefix = "rest_vk_properties")
public class BotProperties {

    private String verifyToken;

    private int groupId;

    private String accessToken;

    private int serverId;

    private String apiVersion;

    private String confirmationCode;

    @Valid
    private Webhook webhook = new Webhook();

    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String version) {
        this.apiVersion = apiVersion;
    }

    public Webhook getWebhook() {
        return webhook;
    }

    public void setWebhook(Webhook webhook) {
        this.webhook = webhook;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public static class Webhook {

        @NotNull
        @Pattern(regexp = "/[^?#]*", message = "Path must start with /")
        private String path = "/webhook";

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
