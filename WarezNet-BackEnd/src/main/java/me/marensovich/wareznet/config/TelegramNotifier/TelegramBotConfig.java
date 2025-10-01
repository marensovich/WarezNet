package me.marensovich.wareznet.config.TelegramNotifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the Telegram bot.
 * <p>
 * Loads bot token and chat ID from environment variables and
 * provides a {@link TelegramBotNotifier} bean for sending messages.
 * </p>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@Configuration
public class TelegramBotConfig {

    /**
     * Telegram bot token, loaded from environment variable TELEGRAM_LOGGER_TOKEN.
     *
     * @since v.0.1
     */
    String botToken = System.getenv("TELEGRAM_LOGGER_TOKEN");

    /**
     * Telegram chat ID, loaded from environment variable TELEGRAM_LOGGER_CHAT_ID.
     *
     * @since v.0.1
     */
    String chatId = System.getenv("TELEGRAM_LOGGER_CHAT_ID");

    /**
     * Creates a {@link TelegramBotNotifier} bean with the configured bot token and chat ID.
     *
     * @return a configured {@link TelegramBotNotifier} instance
     * @since v.0.1
     */
    @Bean
    public TelegramBotNotifier telegramBotNotifier() {
        return new TelegramBotNotifier(botToken, chatId);
    }
}