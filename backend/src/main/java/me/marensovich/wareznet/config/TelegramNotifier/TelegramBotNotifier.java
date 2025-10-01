package me.marensovich.wareznet.config.TelegramNotifier;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Notifier for sending messages to a Telegram chat using a bot.
 * <p>
 * This class provides functionality to send messages with MarkdownV2 formatting
 * to a configured chat ID via the Telegram Bot API.
 * </p>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
public class TelegramBotNotifier {

    /**
     * Telegram bot token used to authenticate requests.
     *
     * @since v.0.1
     */
    private final String botToken;

    /**
     * Telegram chat ID to which messages will be sent.
     *
     * @since v.0.1
     */
    private final String chatId;

    /**
     * ObjectMapper for serializing payloads to JSON.
     *
     * @since v.0.1
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates a new Telegram bot notifier.
     *
     * @param botToken the bot token for authentication
     * @param chatId the chat ID where messages will be sent
     * @since v.0.1
     */
    public TelegramBotNotifier(String botToken, String chatId) {
        this.botToken = botToken;
        this.chatId = chatId;
    }

    /**
     * Sends a message to the configured Telegram chat.
     * <p>
     * The message is sent in MarkdownV2 format. Any exceptions are logged to
     * standard error.
     * </p>
     *
     * @param message the message text to send
     * @since v.0.1
     */
    public void sendMessage(String message) {
        try {
            Map<String, Object> payload = Map.of(
                    "chat_id", chatId,
                    "text", message,
                    "parse_mode", "MarkdownV2"
            );

            String json = objectMapper.writeValueAsString(payload);

            String urlString = "https://api.telegram.org/bot" + botToken + "/sendMessage";
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            if (connection.getErrorStream() != null) {
                String errorResponse = new BufferedReader(new InputStreamReader(connection.getErrorStream()))
                        .lines().collect(Collectors.joining("\n"));
                System.err.println("Telegram error: " + errorResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Escapes a string for MarkdownV2 formatting.
     * <p>
     * All special MarkdownV2 characters are escaped so that they appear literally
     * in the Telegram message.
     * </p>
     *
     * @param text the input text
     * @return the escaped text suitable for MarkdownV2
     * @since v.0.1
     */
    public static String escapeMarkdownV2(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("_", "\\_")
                .replace("*", "\\*")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("~", "\\~")
                .replace("`", "\\`")
                .replace(">", "\\>")
                .replace("#", "\\#")
                .replace("+", "\\+")
                .replace("-", "\\-")
                .replace("=", "\\=")
                .replace("|", "\\|")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace(".", "\\.")
                .replace("!", "\\!");
    }
}