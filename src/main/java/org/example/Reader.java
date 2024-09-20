package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static void readFromJson(File file, Statistic statistic) throws IOException {
        List<Item> list = new ArrayList<>();
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(file);
        JsonToken current = jp.nextToken();
        while(jp.nextToken() != null){
            if(current == JsonToken.START_ARRAY){
                int i = 0;
                while (i < 10000) {
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
                    statistic.duplicatesItems(list);
                }
                list = new ArrayList<>();
            }
        }
    }

    public static void readFromCsv(BufferedReader bufferedReader, Statistic statistic) throws IOException{
        List<Item> list = new ArrayList<>();
        if(bufferedReader.ready()) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                int i = 0;
                while (bufferedReader.ready() && i < 10000) {
                    String[] line = bufferedReader.readLine().trim().split(",");
                    list.add(new Item(
                            line[0].trim(),
                            line[1].trim(),
                            Long.valueOf(line[2]),
                            Long.valueOf(line[3])
                    ));
                    i++;
                }
                if(i > 0) {
                    statistic.sumWeightByGroup(list);
                    statistic.minAndMaxWeight(list);
                    statistic.duplicatesItems(list);
                }
                list = new ArrayList<>();
            }
        }
    }
}
