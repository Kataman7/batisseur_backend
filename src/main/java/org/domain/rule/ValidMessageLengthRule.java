package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.Chat;

public class ValidMessageLengthRule implements GameRule
{
    private final String message;

    public ValidMessageLengthRule(String message) {
        this.message = message;
    }

    @Override
    public boolean isApplicable(Board board)
    {
        return message != null && !message.trim().isEmpty() && message.length() <= Chat.MAX_MESSAGE_LENGTH;
    }

    @Override
    public int getCode()
    {
        return 0;
    }
}
