package com.company;


/*Генерация списка контактов и групп контактов через консольный UI по выбору. Особо жёстких проверок на корректность ввода не прикручивал, но всё можно допилить опционально.
Основной упор сделан непосредственно на генерацию и на запись/чтение в файлы. Генерация ФИО, адреса осуществляется по подготовленным шаблонам со списком возможных значений,
Наименованияже групп генерируются случайным образом*/

public class Main {

    public static void main(String[] args) {
	// write your code here
        Controller controller = new Controller();
        UIController ui = new UIController();
        controller.initProgram();
        ui.inputHandler();
    }


}
