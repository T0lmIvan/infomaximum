package org.example;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Statistic {
    public Map<String, BigInteger> mapSumWeightByGroup = new HashMap<>();
    public Map<String, Long> mapDuplicatesItems = new HashMap<>();
    public long minWeight;
    public long maxWeight;
    public boolean first = false;
    public void outStatistic(){
        System.out.println("    Суммарный вес по группе");
        System.out.printf("%-21s %s\n", "Группа", "Вес");
        for(String key: mapSumWeightByGroup.keySet()){
            System.out.printf("%-15s %s\n", key, mapSumWeightByGroup.get(key));
        }
        System.out.println("==============================================================");

        System.out.println("    Максимальный и минимальный вес");
        System.out.println("Минимальный вес:  " + minWeight);
        System.out.println("Максимальный вес: " + maxWeight);
        System.out.println("==============================================================");

        System.out.println("    Дубликаты объетов");
        System.out.printf("%-21s %-18s %s\n", "Группа", "Тип", "Количество");
    }

    public void sumWeightByGroup(List<Item> items){
        for(Item obj: items){
            if(mapSumWeightByGroup.containsKey(obj.getGroup())){
                mapSumWeightByGroup.put(obj.getGroup(), mapSumWeightByGroup.get(obj.getGroup()).add(new BigInteger(obj.getWeight().toString())));
            } else {
                mapSumWeightByGroup.put(obj.getGroup(), new BigInteger(obj.getWeight().toString()));
            }
        }
    }

    public void minAndMaxWeight(List<Item> items){
        long min = items.stream().map(e -> e.getWeight()).min(Long::compareTo).get();
        long max = items.stream().map(e -> e.getWeight()).max(Long::compareTo).get();
        if(!first){
            minWeight = min;
            maxWeight = max;
        } else {
            if (minWeight > min) minWeight = min;
            if (maxWeight < max) maxWeight = max;
        }
    }



    public void duplicatesItems(List<Item> items){
        items.stream()
                .collect(Collectors.groupingBy(Item::grouping, Collectors.counting()))
                .entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
        System.gc();

    }
}
