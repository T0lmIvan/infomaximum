package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public static void readFromJson(File file, Statistic statistic) throws IOException {
        List<Item> list = new ArrayList<>();
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(file);
        JsonToken current = jp.nextToken();
        int i = 0;
        while(jp.nextToken() != null){
            if(current == JsonToken.START_ARRAY){
                i = 0;
                while (i < 100) {
                    JsonNode node = jp.readValueAsTree();
                    if(!node.toString().equals("null")){
                        list.add(new ObjectMapper().treeToValue(node, Item.class));
                        i++;
                    } else {
                        break;
                    }
                }
                if(i > 0) {
                    statistic.sumWeightByGroup(list);
                    statistic.minAndMaxWeight(list);
                }
                list = new ArrayList<>();
            }
        }
        if (i > 0) {
            statistic.outStatistic();
        }
        System.gc();
        readJson(file, statistic);
        System.out.println("==============================================================");
    }

    public static void readJson(File file, Statistic statistic) throws IOException{
        List<String> groups = new ArrayList<>();
        List<Item> list = new ArrayList<>();
        while (true){
            JsonFactory f = new MappingJsonFactory();
            JsonParser jp = f.createJsonParser(file);
            JsonToken current = jp.nextToken();
            String currentGroup = "";
            while (jp.nextToken() != null) {
                JsonNode node = jp.readValueAsTree();
                if(!node.toString().equals("null")){
                    if(!groups.contains(node.get("group").toString())) {
                        currentGroup = node.get("group").toString();
                        groups.add(currentGroup);
                        list.add(new ObjectMapper().treeToValue(node, Item.class));
                    } else {
                        break;
                    }
                }
            }
            if(currentGroup.equals("")){
                break;
            }
            while (jp.nextToken() != null) {
                JsonNode node = jp.readValueAsTree();
                if(!node.toString().equals("null") && currentGroup.equals(node.get("group").toString())) {
                    list.add(new ObjectMapper().treeToValue(node, Item.class));
                }
            }
            if (list.size() > 0) {
                statistic.duplicatesItems(list);
            }
            list = new ArrayList<>();
        }
    }

    public static void readFromCsv(File file, Statistic statistic) throws IOException{
        List<Item> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        if (bufferedReader.ready()) {
            bufferedReader.readLine();
            int i = 0;
            while (bufferedReader.ready()) {
                i = 0;
                while (bufferedReader.ready() && i < 100) {
                    String[] line = bufferedReader.readLine().trim().split(",");
                    list.add(new Item(
                            line[0].trim(),
                            line[1].trim(),
                            Long.valueOf(line[2]),
                            Long.valueOf(line[3])
                    ));
                    i++;
                }
                if (i > 0) {
                    statistic.sumWeightByGroup(list);
                    statistic.minAndMaxWeight(list);
                }
                list = new ArrayList<>();
            }
            if (i > 0) {
                statistic.outStatistic();
            }
            System.gc();
            readCsv(file, statistic);
            System.out.println("==============================================================");
        }
    }

    public static void readCsv(File file, Statistic statistic) throws IOException{
        List<String> groups = new ArrayList<>();
        List<Item> list = new ArrayList<>();
        while (true){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            String currentGroup = "";
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().trim().split(",");
                if (!groups.contains(line[0])) {
                    currentGroup = line[0];
                    groups.add(currentGroup);
                    list.add(new Item(
                            line[0].trim(),
                            line[1].trim(),
                            Long.valueOf(line[2]),
                            Long.valueOf(line[3])
                    ));
                    break;
                }
            }
            if(currentGroup.equals("")){
                break;
            }
            int i = 0;
            while (bufferedReader.ready()) {
                ++i;
                String[] line = bufferedReader.readLine().trim().split(",");
                if(currentGroup.equals(line[0])){
                    list.add(new Item(
                            line[0].trim(),
                            line[1].trim(),
                            Long.valueOf(line[2]),
                            Long.valueOf(line[3])
                    ));
                }
            }
            if(list.size() > 0){
                statistic.duplicatesItems(list);
            }
            list = new ArrayList<>();
        }
    }
}
