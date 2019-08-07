package com.company;

import java.nio.file.Paths;



public class Controller {
    private DataGenerator dataGenerator = new DataGenerator();

    /*Загрузка шаблонов для генерации*/
    public void initProgram(){
        dataGenerator.setMaleFirstnameDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getMaleFirstnameList()).toString()));
        dataGenerator.setMaleSecondnameDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getMaleSecondnameList()).toString()));
        dataGenerator.setMaleLastnameDictionary (FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getMaleLastnameList()).toString()));
        dataGenerator.setFemaleFirstnameDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getFemaleFirstnameList()).toString()));
        dataGenerator.setFemaleSecondnameDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getFemaleSecondnameList()).toString()));
        dataGenerator.setFemaleLastnameDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getFemaleLastnameList()).toString()));
        dataGenerator.setPhonePrefixDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getPhonePrefix()).toString()));
        dataGenerator.setAddressDictionary(FileWorker.dictionaryReader(Paths.get(FileWorker.getDefaultInputPath() + FileWorker.getAddressList()).toString()));
        }
    /*Генерация списка контактов и запись их в JSON файл*/
    public String generatePersonList(int size){
        String executeMessage = FileWorker.writeJSONFile(Paths.get(FileWorker.getDefaultOutputPath() +  FileWorker.getPersonListFileName()).toString(),dataGenerator.generatePersonList(size));
        return executeMessage;
    }
    /*Генерация сиска групп контактов  и запись их в JSON файл*/
    public String generateGroupList(int size) {
        String executeMessage = FileWorker.writeJSONFile(Paths.get(FileWorker.getDefaultOutputPath() + FileWorker.getGroupListFileName()).toString(), dataGenerator.generateGroupList(size, FileWorker.GUIDReader(Paths.get(FileWorker.getDefaultOutputPath() + FileWorker.getPersonListFileName()).toString())));
        return executeMessage;
    }
}
