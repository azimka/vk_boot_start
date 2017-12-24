package webhook;

import vkbot.VkBot;

public interface CallbackHandler {
    void handleCallback(VkBot vkBot, String payload);
}
