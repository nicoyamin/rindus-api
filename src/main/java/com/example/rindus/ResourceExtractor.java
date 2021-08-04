package com.example.rindus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.XML;
import reactor.core.publisher.Mono;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class ResourceExtractor {

    private static final String JSON = ".json";
    private static final String XML = ".xml";


    public static void extractToJson(String fileName, Mono data) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File newFile = new File(ApiConstants.EXTRACTED_PATH+fileName+JSON);
        FileWriter writer = new FileWriter(newFile);

        newFile.createNewFile();
        gson.toJson(data.block(), writer);
        writer.close();

    }


    public static void extractToXml(String fileName, Mono data, String tagName) throws IOException {

        Gson gson = new Gson();
        JSONArray jsonArray = new JSONArray(gson.toJson(data.block()));
        String xml = org.json.XML.toString(jsonArray, tagName);
        File newFile = new File(ApiConstants.EXTRACTED_PATH+fileName+XML);
        FileWriter writer = new FileWriter(newFile);
        writer.write(xml);
        writer.close();

    }
}
