package org.domain.rules;

import org.domain.model.Board;

public class ValidBuildersDataRule implements GameRule
{
    private final String[][] buildersData;

    public ValidBuildersDataRule(String[][] buildersData) {
        this.buildersData = buildersData;
    }

    @Override
    public boolean isApplicable(Board board) {
        if (buildersData == null || buildersData.length == 0)
        {
            return false;
        }
        for (String[] builderData : buildersData)
        {
            if (builderData == null || builderData.length < 6)
            {
                return false;
            }
            if (builderData[0].isEmpty() || !builderData[0].toLowerCase().equals(builderData[0])) //name
            {
                return false;
            }
            try
            {
                if (Integer.parseInt(builderData[1]) < 0) // cost
                {
                    return false;
                }
                Integer.parseInt(builderData[2]); // ressource1
                Integer.parseInt(builderData[3]); // ressource2
                Integer.parseInt(builderData[4]); // ressource3
                Integer.parseInt(builderData[5]); // ressource4
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
