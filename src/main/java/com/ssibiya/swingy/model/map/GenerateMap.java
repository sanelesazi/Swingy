package com.ssibiya.swingy.model.map;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
public class GenerateMap {
    @Getter @Setter
    public static char [][]map;
    protected int size;

    public GenerateMap(int level)
    {
        size = generateSize(level);
        map = new char[size][size];
        initNewMap();
    }

    private int generateSize(int level)
    {
        return ((level - 1) * 5 + 10 - (level % 2));
    }

    public void initNewMap()
    {
        for (char [] row: map)
            Arrays.fill(row, '0');
    }
}
