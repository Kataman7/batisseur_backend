package org.domain.model;

import lombok.Getter;
import javax.json.Json;
import javax.json.JsonObject;

@Getter
public class Resources implements Model
{
    private int res1;
    private int res2;
    private int res3;
    private int res4;

    public Resources(int[] ressources)
    {
        this.res1 = ressources.length > 0 ? ressources[0] : 0;
        this.res2 = ressources.length > 1 ? ressources[1] : 0;
        this.res3 = ressources.length > 2 ? ressources[2] : 0;
        this.res4 = ressources.length > 3 ? ressources[3] : 0;
    }
    public boolean isEmpty()
    {
        return res1 <= 0 && res2 <= 0 && res3 <= 0 && res4 <= 0;
    }
    public void remove(Resources r)
    {
        this.res1 -= r.res1;
        this.res2 -= r.res2;
        this.res3 -= r.res3;
        this.res4 -= r.res4;
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("res1", res1)
                .add("res2", res2)
                .add("res3", res3)
                .add("res4", res4)
                .build();
    }
}
