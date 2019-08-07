package com.company;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UIController {
    Scanner in = new Scanner(System.in);
    public Controller controller = new Controller();
    private final String GeneratePerson = ".*-GP.*";
    private final String GenerateGroup = ".*-GG.*";
    private final String ChangePersonFileName = ".*-CP.*";
    private final String ChangeGroupFileName = ".*-CG.*";
    private final String Exit = ".*EXIT.*";
    private final String Help = ".*HELP.*";
    private final String IsNumber = "^[0-9]+$";
    public void fileCreationMessage(String message) {
        System.out.println("Файл успешно создан по пути: " + message);
    }
    public void help() {
        System.out.println( "-gp - Генерация списка контактов");
        System.out.println( "-gg - Генерация групп контактов");
        System.out.println( "-сp - Изменить имя файла сгенерированных контактов");
        System.out.println( "-сg - Изменить имя файла сгенерированных групп");
        System.out.println( "exit - Выход");
    }
    public void inputHandler() {
        System.out.println("Добро пожаловать в генератор данных!");
        System.out.println("Для получения справки о возможных коммандах введите help");
        for(;;){
            System.out.println("Введите новую команду");
            String inputCommand = in.nextLine();
            if (Pattern.matches(GeneratePerson, inputCommand.toUpperCase())) {
                System.out.println("Генерация контактов");
                System.out.println("Введите число генерируемых контактов:");
                inputCommand = in.nextLine();
                if (Pattern.matches(IsNumber, inputCommand)) fileCreationMessage(controller.generatePersonList(Integer.parseInt(inputCommand)));
                else System.out.println("Некорректно введено число генерируемых данных");
            }
            else if (Pattern.matches(GenerateGroup, inputCommand.toUpperCase())) {
                System.out.println("Генерация групп:");
                System.out.println("Введите число генерируемых групп:");
                inputCommand = in.nextLine();
                if (Pattern.matches(IsNumber, inputCommand)) fileCreationMessage(controller.generateGroupList(Integer.parseInt(inputCommand)));
                else System.out.println("Некорректно введено число генерируемых данных");

            }
            else if (Pattern.matches(ChangePersonFileName, inputCommand.toUpperCase())) {
                System.out.println("Смена имени файла списка контактов");
                System.out.println("Введите новое имя файла:");
                inputCommand = in.nextLine();
                FileWorker.setPersonListFileName(inputCommand + ".json");
            }
            else if (Pattern.matches(ChangeGroupFileName, inputCommand.toUpperCase())) {
                System.out.println("Смена имени файла списка групп");
                System.out.println("Введите новое имя файла:");
                inputCommand = in.nextLine();
                FileWorker.setGroupListFileName(inputCommand + ".json");
            }
            else if (Pattern.matches(Exit, inputCommand.toUpperCase())) {
                System.out.println("Выход");
                break;
            }
            else if (Pattern.matches(Help, inputCommand.toUpperCase())) {
                help();
            }
            else System.out.println("Неопознанная команда");
        }
    }

}
