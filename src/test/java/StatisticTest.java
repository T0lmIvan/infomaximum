import org.example.Item;
import org.example.Statistic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticTest {
    Statistic statistic = new Statistic();
    static List<Item> items = new ArrayList<>(Arrays.asList(
            new Item("AdOkreKceJuDV","NuZslSxVeClOkl",8604139765641617560l,6559786417815524764l),
            new Item("SVyaNIYdZqds","YvCmLDJAFhXdra",9184503979634907103l,396034632740568565l),
            new Item("nILDLbDoKorqGm","AMMbDQYoAabRty",2527903346349552878l,2532307815409109442l),
            new Item("uzwAxztZtqRlk","itKQzYGhTPlC",8289892457942474323l,166245166882415069l),
            new Item("ZgfVWKmfyHRUDR","yQjuyYkikeMCk",1411629996999172758l,5552420543785578097l),
            new Item("TNTsvztCeeQ","KnRwcmkKjPKRt",2780238576073432531l,7929600365256606300l)
    ));




    @Test
    void sumWeightByGroup(){
        statistic.sumWeightByGroup(items);
            Map<String, BigInteger> map = new HashMap<>(){{
                put("TNTsvztCeeQ",new BigInteger(7929600365256606300l + ""));
                put("SVyaNIYdZqds",new BigInteger(396034632740568565l + ""));
                put("nILDLbDoKorqGm",new BigInteger(2532307815409109442l + ""));
                put("ZgfVWKmfyHRUDR",new BigInteger(5552420543785578097l + ""));
                put("AdOkreKceJuDV",new BigInteger(6559786417815524764l + ""));
                put("uzwAxztZtqRlk",new BigInteger(166245166882415069l + ""));
            }};
        Assertions.assertEquals(statistic.mapSumWeightByGroup, map);
    }
    @Test
    void minAndMaxWeight(){
        statistic.minAndMaxWeight(items);
        long min = 166245166882415069l;
        long max = 7929600365256606300l;
        Assertions.assertEquals(statistic.minWeight, min);
        Assertions.assertEquals(statistic.maxWeight, max);
    }
    @Test
    void duplicatesItems(){
        statistic.duplicatesItems(items);
        Map<String, Long> map = new HashMap<>();
        map.put("AdOkreKceJuDV   NuZslSxVeClOkl ",1l);
        map.put("nILDLbDoKorqGm  AMMbDQYoAabRty ",1l);
        map.put("ZgfVWKmfyHRUDR  yQjuyYkikeMCk  ",1l);
        map.put("TNTsvztCeeQ     KnRwcmkKjPKRt  ",1l);
        map.put("SVyaNIYdZqds    YvCmLDJAFhXdra ",1l);
        map.put("uzwAxztZtqRlk   itKQzYGhTPlC   ",1l);
        Assertions.assertEquals(statistic.mapDuplicatesItems, map);

    }
}
