package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class FileWorker {
    private static FileWriter fileWriter;
    private static FileReader fileReader;
    private static String DefaultInputPath = "src/com/company/dicts/";
    private static String DefaultOutputPath = "src/com/company/dicts/";
    private static String PersonListFileName = "generatedPersonsList.json";
    private static String GroupListFileName = "generatedGroupList.json";
    private static String MaleFirstnameList = "maleFirstnameList.txt";
    private static String MaleSecondnameList = "maleSecondnameList.txt";
    private static String MaleLastnameList = "maleLastnameList.txt";
    private static String FemaleFirstnameList = "femaleFirstnameList.txt";
    private static String FemaleSecondnameList = "femaleSecondnameList.txt";
    private static String FemaleLastnameList = "femaleLastnameList.txt";
    private static String PhonePrefix = "phonePrefix.txt";
    private static String AddressList = "addressList.txt";
    /*Чтение словарей шаблонов для генерации контактов из файлов в словарь*/
    public static Map<Integer, String> dictionaryReader(String filePath) {
        try
        {
            fileReader=new FileReader(filePath);
            Scanner scan = new Scanner(fileReader);
            int index = 0;
            Map<Integer, String> dictionary = new HashMap<Integer, String>();
            String s = "";
            while (scan.hasNextLine())
            {
                s = scan.nextLine();
                dictionary.put(index, s);
                index++;
            }
            fileReader.close();
            return dictionary;
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
            return null;
        }

    }
    /*Запись JSON объекта в файл*/
    public static String writeJSONFile(String filePath, JSONObject obj){
            if (obj != null) {
                try {
                    fileWriter = new FileWriter(filePath);
                    fileWriter.write(obj.toJSONString());
                    fileWriter.close();
                    File file = new File(filePath);
                    return Paths.get(file.getAbsolutePath()).toString();
                } catch (IOException e) {
                    System.out.println(e);
                    return "Ошибка создания файла";
                }
            }
            else return "Ошибка создания файла";
    }
    /*Чтение списка существующих GUID'ов для последующей генерации групп контактов*/
    public static ArrayList<Integer> GUIDReader(String filePath){
        JSONParser jsonParser = new JSONParser();
        ArrayList<Integer> guidSet = new ArrayList<Integer>();
        try {
            File f = new File(filePath);
            if(f.exists() && !f.isDirectory()){
            fileReader = new FileReader(filePath);

            //Read JSON file
            JSONObject storedObject = (JSONObject) jsonParser.parse(fileReader);
            JSONArray personList = (JSONArray) storedObject.get("Список контактов");
            for (Object person : personList) {

                JSONObject personRow = (JSONObject) person;
                //guidSet.add(Integer.parseInt(((String)personRow.get("GUID"))));
                guidSet.add((int)(long)(Long)personRow.get("GUID"));
            }
            fileReader.close();
            }
            else {
                System.out.println("Файл контактов отсутствует или не был создан");
                return  null;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return guidSet;
    }

    public static String getDefaultInputPath() {
        return DefaultInputPath;
    }

    public static void setDefaultInputPath(String defaultInputPath) {
        DefaultInputPath = defaultInputPath;
    }

    public static String getDefaultOutputPath() {
        return DefaultOutputPath;
    }

    public static void setDefaultOutputPath(String defaultOutputPath) {
        DefaultOutputPath = defaultOutputPath;
    }

    public static String getPersonListFileName() {
        return PersonListFileName;
    }

    public static void setPersonListFileName(String personListFileName) {
        PersonListFileName = personListFileName;
    }

    public static String getGroupListFileName() {
        return GroupListFileName;
    }

    public static void setGroupListFileName(String groupListFileName) {
        GroupListFileName = groupListFileName;
    }

    public static String getMaleFirstnameList() {
        return MaleFirstnameList;
    }

    public static void setMaleFirstnameList(String maleFirstnameList) {
        MaleFirstnameList = maleFirstnameList;
    }

    public static String getMaleSecondnameList() {
        return MaleSecondnameList;
    }

    public static void setMaleSecondnameList(String maleSecondnameList) {
        MaleSecondnameList = maleSecondnameList;
    }

    public static String getMaleLastnameList() {
        return MaleLastnameList;
    }

    public static void setMaleLastnameList(String maleLastnameList) {
        MaleLastnameList = maleLastnameList;
    }

    public static String getFemaleFirstnameList() {
        return FemaleFirstnameList;
    }

    public static void setFemaleFirstnameList(String femaleFirstnameList) {
        FemaleFirstnameList = femaleFirstnameList;
    }

    public static String getFemaleSecondnameList() {
        return FemaleSecondnameList;
    }

    public static void setFemaleSecondnameList(String femaleSecondnameList) {
        FemaleSecondnameList = femaleSecondnameList;
    }

    public static String getFemaleLastnameList() {
        return FemaleLastnameList;
    }

    public static void setFemaleLastnameList(String femaleLastnameList) {
        FemaleLastnameList = femaleLastnameList;
    }

    public static String getPhonePrefix() {
        return PhonePrefix;
    }

    public static void setPhonePrefix(String phonePrefix) {
        PhonePrefix = phonePrefix;
    }

    public static String getAddressList() {
        return AddressList;
    }

    public static void setAddressList(String addressList) {
        AddressList = addressList;
    }
}
