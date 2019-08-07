package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

public class DataGenerator {
    private final Random random = new Random();
    private static int lastGUID = 0;
    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUUVWXYZ";
    private static Map<Integer,String> maleFirstnameDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> maleSecondnameDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> maleLastnameDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> femaleFirstnameDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> femaleSecondnameDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> femaleLastnameDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> phonePrefixDictionary = new HashMap<Integer, String>();
    private static Map<Integer,String> addressDictionary = new HashMap<Integer, String>();
    public static final String LFS = "ФИО";
    public static final String Address = "Адрес";
    public static final String PhoneNumber = "Номер телефона";
    public static final String GUID = "GUID";
    public static final String ContactList= "Список контактов";
    public static final String GroupName= "Имя группы";
    public static final String GroupMembers= "Члены группы";
    public static final String GroupList= "Список групп";

    /*Генерация контакта на основе шаблонов. ФИО, адрес : улица, дом, квартира, номер телефона и GUID. GUID здесь начинается  с0*/
    public JSONObject generatePreson(){
        String personLFS = "";
        JSONObject person = new JSONObject();
        switch (random.nextInt(2)){
            case 0: { personLFS = generateMale(); break; }
            case 1: { personLFS = generateFemale(); break; }
        }
        person.put(LFS, personLFS);
        person.put(Address, addressGenerator(addressDictionary));
        person.put(PhoneNumber, phoneGenerator(phonePrefixDictionary));
        person.put(GUID, getLastGUID());
        return person;

    }
    /*Формирование списка контактов нужной длины*/
    public JSONObject generatePersonList( int listSize) {
        JSONArray jsonList = new JSONArray();
        for (int i = 0; i < listSize; i++){
            jsonList.add(generatePreson());
        }
        JSONObject obj = new JSONObject();
        obj.put(ContactList, jsonList);
        return obj;
    }
    /*Генерация списка групп контактов нужной длины.*/
    public JSONObject generateGroupList( int listSize, ArrayList<Integer> guidList ) {
        if (guidList != null) {
            JSONArray jsonList = new JSONArray();
            for (int i = 0; i < listSize; i++) {
                jsonList.add(generateGroup(guidList));
            }
            JSONObject obj = new JSONObject();
            obj.put(GroupList, jsonList);
            return obj;
        }
        else return null;
    }
    /*Генерация групп контактов случайной длины*/
    public JSONObject generateGroup (ArrayList<Integer> guidList){
        if (guidList != null) {
            String groupName = groupNameGenerator();
            ArrayList<Integer> groupMemberList = groupGenerator(guidList, random.nextInt(guidList.size()) + 1);
            JSONArray groupMemberJSONArray = new JSONArray();
            for (Integer guid : groupMemberList) groupMemberJSONArray.add(guid);
            JSONObject group = new JSONObject();
            group.put(GroupName, groupName);
            group.put(GroupMembers, groupMemberJSONArray);
            return group;
        }
        else return null;
    }
    /*Генерация случайного мужчины*/
    private String generateMale(){
        return nameGenerator(maleLastnameDictionary) + " " + nameGenerator(maleFirstnameDictionary) + " " + nameGenerator(maleSecondnameDictionary);
    }
    /*Генерация случайной женщины*/
    private String generateFemale(){
        return nameGenerator(femaleLastnameDictionary) + " " + nameGenerator(femaleFirstnameDictionary) + " " + nameGenerator(femaleSecondnameDictionary);
    }
    /*Вытаскивает случайную позицию из списка шоблонов для генерации*/
    public String nameGenerator(Map<Integer,String> nameList){
        return nameList.get(random.nextInt(Collections.max(nameList.keySet())+1));
    }

    /*Генерация случайного номера телефона*/
    public String phoneGenerator(Map<Integer,String> phonePrefixList){
        String phoneNumber = "";
        phoneNumber = "+7" + phonePrefixList.get(random.nextInt(Collections.max(phonePrefixList.keySet())+1)) + Integer.toString(random.nextInt(10))
                + Integer.toString(random.nextInt(10)) + Integer.toString(random.nextInt(10)) + Integer.toString(random.nextInt(10)) + Integer.toString(random.nextInt(10))
                + Integer.toString(random.nextInt(10)) + Integer.toString(random.nextInt(10));
        return phoneNumber;
    }
    /*Генерация случайного адреса*/
    public String addressGenerator(Map<Integer,String> addressList){
        String address = "";
        address = addressList.get(random.nextInt(Collections.max(addressList.keySet())+1)) + " д." + Integer.toString(random.nextInt(100) + 1) + " кв." + Integer.toString(random.nextInt(256) + 1);
        return address;
    }
    /*Простенькое инкрементное формирование GUID без особых требований*/
    public int getLastGUID() {
        return lastGUID++;
    }
    /*Генерация групп guid'ов без повторений*/
    public ArrayList<Integer> groupGenerator(ArrayList<Integer> guidList, int groupCount) {
        ArrayList<Integer> group = new ArrayList<Integer>();
        Collections.shuffle(guidList);
        for (Integer guid: guidList) {
            group.add(guid);
            if (group.size() == groupCount) break;
        }
        return group;
    }
    /*генерация случайного имени группы контактов*/
    public String groupNameGenerator() {
        String name = "";
        for (int i = 0; i < random.nextInt(8)+ 5; i++) {
            name = name + alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return name;
    }

    public void setMaleFirstnameDictionary(Map<Integer, String> maleFirstnameDictionary) {
        this.maleFirstnameDictionary = maleFirstnameDictionary;
    }

    public void setMaleSecondnameDictionary(Map<Integer, String> maleSecondnameDictionary) {
        this.maleSecondnameDictionary = maleSecondnameDictionary;
    }

    public void setMaleLastnameDictionary(Map<Integer, String> maleLastnameDictionary) {
        this.maleLastnameDictionary = maleLastnameDictionary;
    }

    public void setFemaleFirstnameDictionary(Map<Integer, String> femaleFirstnameDictionary) {
        this.femaleFirstnameDictionary = femaleFirstnameDictionary;
    }

    public void setFemaleSecondnameDictionary(Map<Integer, String> femaleSecondnameDictionary) {
        this.femaleSecondnameDictionary = femaleSecondnameDictionary;
    }

    public void setFemaleLastnameDictionary(Map<Integer, String> femaleLastnameDictionary) {
        this.femaleLastnameDictionary = femaleLastnameDictionary;
    }

    public void setPhonePrefixDictionary(Map<Integer, String> phonePrefixDictionary) {
        this.phonePrefixDictionary = phonePrefixDictionary;
    }

    public void setAddressDictionary(Map<Integer, String> addressDictionary) {
        this.addressDictionary = addressDictionary;
    }
}
