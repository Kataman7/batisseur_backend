package org.domain.rules;

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
        if (buildsData == null || buildsData.length == 0)
        {
            return false;
        }
        for (String[] buildData : buildsData)
        {
            if (buildData == null || buildData.length < 9)
            {
                return false;
            }
            if (buildData[0].isEmpty() || !buildData[0].toLowerCase().equals(buildData[0])) //name
            {
                return false;
            }
            try
            {
                if (Integer.parseInt(buildData[1]) == 0 || Integer.parseInt(buildData[2]) == 0) // size x and y
                {
                    return false;
                }
                Integer.parseInt(buildData[3]); // cost
                Integer.parseInt(buildData[4]); // reward
                Integer.parseInt(buildData[5]); // ressource1
                Integer.parseInt(buildData[6]); // ressource2
                Integer.parseInt(buildData[7]); // ressource3
                Integer.parseInt(buildData[8]); // ressource4
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        return true;
    }
    @Override
    public int getCode()
    {
        return 0;
    }
}
