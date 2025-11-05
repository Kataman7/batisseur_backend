package org.domain.rule;

import org.domain.model.Board;

public class ValidBuildersDataRule implements GameRule
{
    private final String[][] buildersData;

    public ValidBuildersDataRule(String[][] buildersData)
    {
        this.buildersData = buildersData;
    }

    @Override
    public boolean isApplicable(Board board)
    {
        if (isBuildersDataNullOrEmpty()) return false;
        for (String[] builderData : buildersData)
        {
            if (!isBuilderDataValid(builderData)) return false;
        }
        return true;
    }

    private boolean isBuildersDataNullOrEmpty()
    {
        return buildersData == null || buildersData.length == 0;
    }

    private boolean isBuilderDataValid(String[] builderData) {
        return isBuilderDataNotNullAndComplete(builderData)
                && isBuilderNameValid(builderData[0])
                && isBuilderNumbersValid(builderData);
    }

    private boolean isBuilderDataNotNullAndComplete(String[] builderData) {
        return builderData != null && builderData.length >= 6;
    }

    private boolean isBuilderNameValid(String name) {
        return !name.isEmpty() && name.equals(name.toLowerCase());
    }

    private boolean isBuilderNumbersValid(String[] builderData) {
        try {
            return isCostValid(builderData[1])
                    && isInteger(builderData[2])
                    && isInteger(builderData[3])
                    && isInteger(builderData[4])
                    && isInteger(builderData[5]);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCostValid(String cost) {
        return Integer.parseInt(cost) >= 0;
    }

    private boolean isInteger(String value) {
        Integer.parseInt(value);
        return true;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
