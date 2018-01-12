package com.carin.doninelli.midna.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;

public final class ResponseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseService.class);

    private final boolean retryOnFailure;

    public ResponseService(boolean retryOnFailure) {
        this.retryOnFailure = retryOnFailure;
    }

    public void bufferResponseRequest(MessageBuilder message) {
        RequestBuffer.request(() -> {
            try {
                message.send();
            } catch (RateLimitException ex) {
                if (retryOnFailure) {
                    LOGGER.info("Rate Limit hit. Retrying failed request...");
                    throw ex;
                } else {
                    LOGGER.error(ex.getLocalizedMessage(), ex);
                }
            }
        });
    }
}
