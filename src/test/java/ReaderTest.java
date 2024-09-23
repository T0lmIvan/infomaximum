
import org.example.Reader;
import org.example.Statistic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ReaderTest {
    @Test
    public void readFromJson() throws IOException {
        Statistic statistic = new Statistic();
        File file = new File(("src/test/resources/t.json"));
        Reader.readFromJson(file, statistic);
        long min = 166245166882415069l;
        long max = 7929600365256606300l;
        Map<String, BigInteger> mapSum= new HashMap<>(){{
            put("TNTsvztCeeQ",new BigInteger(7929600365256606300l + ""));
            put("SVyaNIYdZqds",new BigInteger(396034632740568565l + ""));
            put("nILDLbDoKorqGm",new BigInteger(2532307815409109442l + ""));
            put("ZgfVWKmfyHRUDR",new BigInteger(5552420543785578097l + ""));
            put("AdOkreKceJuDV",new BigInteger(6559786417815524764l + ""));
            put("uzwAxztZtqRlk",new BigInteger(166245166882415069l + ""));
        }};
        Map<String, Long> mapDubl = new HashMap<>(){{
            put("AdOkreKceJuDV   NuZslSxVeClOkl ", 1l);
            put("nILDLbDoKorqGm  AMMbDQYoAabRty ", 1l);
            put("ZgfVWKmfyHRUDR  yQjuyYkikeMCk  ", 1l);
            put("TNTsvztCeeQ     KnRwcmkKjPKRt  ", 1l);
            put("SVyaNIYdZqds    YvCmLDJAFhXdra ", 1l);
            put("uzwAxztZtqRlk   itKQzYGhTPlC   ", 1l);
        }};
        Assertions.assertEquals(statistic.minWeight, min);
        Assertions.assertEquals(statistic.maxWeight, max);
        Assertions.assertEquals(statistic.mapSumWeightByGroup, mapSum);
        Assertions.assertEquals(statistic.mapDuplicatesItems, mapDubl);

    }

    @Test
    public void readFromCsv() throws IOException{
        Statistic statistic = new Statistic();
        File file = new File("src/test/resources/t.csv");
        Reader.readFromCsv(file, statistic);
        long min = 166245166882415069l;
        long max = 7929600365256606300l;
        Map<String, BigInteger> mapSum= new HashMap<>(){{
            put("TNTsvztCeeQ",new BigInteger(7929600365256606300l + ""));
            put("SVyaNIYdZqds",new BigInteger(396034632740568565l + ""));
            put("nILDLbDoKorqGm",new BigInteger(2532307815409109442l + ""));
            put("ZgfVWKmfyHRUDR",new BigInteger(5552420543785578097l + ""));
            put("AdOkreKceJuDV",new BigInteger(6559786417815524764l + ""));
            put("uzwAxztZtqRlk",new BigInteger(166245166882415069l + ""));
        }};
        Map<String, Long> mapDubl = new HashMap<>(){{
            put("AdOkreKceJuDV   NuZslSxVeClOkl ", 1l);
            put("nILDLbDoKorqGm  AMMbDQYoAabRty ", 1l);
            put("ZgfVWKmfyHRUDR  yQjuyYkikeMCk  ", 1l);
            put("TNTsvztCeeQ     KnRwcmkKjPKRt  ", 1l);
            put("SVyaNIYdZqds    YvCmLDJAFhXdra ", 1l);
            put("uzwAxztZtqRlk   itKQzYGhTPlC   ", 1l);
        }};
        Assertions.assertEquals(statistic.minWeight, min);
        Assertions.assertEquals(statistic.maxWeight, max);
        Assertions.assertEquals(statistic.mapSumWeightByGroup, mapSum);
        Assertions.assertEquals(statistic.mapDuplicatesItems, mapDubl);
    }
}
