package ru.innopolis.stc13.reflection.homeTask;

import java.beans.XMLEncoder;

public class Main {
    public static void main(String[] args) {
        Home home = new Home(3,10, true, "Baker street 101");
        String fileName = "E:\\JAVA Innopolis\\06_reflection_homeTask\\src\\ru\\innopolis\\stc13\\reflection\\homeTask\\test.xml";
        XMLSerializationIO xmlSerializatorIO = new XMLSerializationIO();
        xmlSerializatorIO.serialization(home, fileName);
        Object object = xmlSerializatorIO.deSerialization(fileName);
        Home home2 = (Home) object;
    }
}
