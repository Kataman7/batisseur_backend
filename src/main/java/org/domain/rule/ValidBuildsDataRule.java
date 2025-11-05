package org.domain.rule;

import org.domain.model.Board;

public class ValidBuildsDataRule implements GameRule
{
    private final String[][] buildsData;

    public ValidBuildsDataRule(String[][] buildsData)
    {
        this.buildsData = buildsData;
    }

    @Override
    public boolean isApplicable(Board board)
    {
        if (isBuildsDataNullOrEmpty()) return false;
        for (String[] buildData : buildsData)
        {
            if (!isBuildDataValid(buildData)) return false;
        }
        return true;
    }

    private boolean isBuildsDataNullOrEmpty() {
        return buildsData == null || buildsData.length == 0;
    }

    private boolean isBuildDataValid(String[] buildData) {
        return isBuildDataNotNullAndComplete(buildData)
                && isBuildNameValid(buildData[0])
                && isBuildNumbersValid(buildData);
    }

    private boolean isBuildDataNotNullAndComplete(String[] buildData) {
        return buildData != null && buildData.length >= 9;
    }

    private boolean isBuildNameValid(String name) {
        return !name.isEmpty() && name.equals(name.toLowerCase());
    }

    private boolean isBuildNumbersValid(String[] buildData) {
        try {
            return isSizeValid(buildData[1], buildData[2])
                    && isInteger(buildData[3]) // cost
                    && isInteger(buildData[4]) // reward
                    && isInteger(buildData[5]) // ressource1
                    && isInteger(buildData[6]) // ressource2
                    && isInteger(buildData[7]) // ressource3
                    && isInteger(buildData[8]); // ressource4
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isSizeValid(String sizeX, String sizeY) {
        return Integer.parseInt(sizeX) != 0 && Integer.parseInt(sizeY) != 0;
    }

    private boolean isInteger(String value) {
        Integer.parseInt(value);
        return true;
    }

    @Override
    public int getCode()
    {
        return 0;
    }
}
