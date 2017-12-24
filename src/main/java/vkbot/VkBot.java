package vkbot;

import autoconfigure.BotProperties;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import webhook.CallbackHandler;

public class VkBot {

    private String verifyToken;

    private int groupId;

    private String accessToken;

    private int serverId;

    private String apiVersion;

    private CallbackHandler handler;

    private VkApiClient vkApiClient;

    private GroupActor groupActor;

    private String confirmationCode;

    public VkBot(BotProperties properties, CallbackHandler callbackHandler) {
        this.verifyToken = properties.getVerifyToken();
        this.groupId = properties.getGroupId();
        this.accessToken = properties.getAccessToken();
        this.serverId = properties.getServerId();
        this.apiVersion = properties.getApiVersion();
        this.handler = callbackHandler;
        this.confirmationCode = properties.getConfirmationCode();

        vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

        groupActor = new GroupActor(groupId, accessToken);
    }

    public void handle(String payload) {
        handler.handleCallback(this, payload);
    }

    public VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    public GroupActor getGroupActor() {
        return groupActor;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }
}
