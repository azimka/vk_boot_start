package webhook;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vk.api.sdk.callback.objects.messages.CallbackMessageType;
import vkbot.VkBot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static java.lang.String.format;

public class WebhookServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(WebhookServlet.class.getName());
    private static final String HANDLE_EXCEPTION_TEMPLATE = "Exception handling webhook: %s";
    private static final String OK_RESPONSE = "ok";
    private static final String RESPONSE_TYPE = "text/html;charset=utf-8";

    private VkBot vkbot;

    public WebhookServlet(VkBot botRequestHandler) {
        this.vkbot = botRequestHandler;
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String payload = getRequestBodyAsString(req);
            vkbot.handle(payload);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(RESPONSE_TYPE);
            resp.getWriter().println(getResponseString(payload));

        } catch (Exception e) {
            logger.severe(format(HANDLE_EXCEPTION_TEMPLATE, e.getMessage()));
            throw e;
        }
    }

    private String getResponseString(String payload) {
        Gson gson = new Gson();
        String type = gson.fromJson(payload, JsonObject.class).get("type").getAsString();
        if (CallbackMessageType.CONFIRMATION.getValue().equals(type)) {
            return vkbot.getConfirmationCode();
        }

        return OK_RESPONSE;
    }


    protected String getRequestBodyAsString(HttpServletRequest req) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
