package ru.innopolis.stc13.reflection.homeTask;

import java.beans.XMLDecoder;
import java.io.*;
import java.lang.reflect.Field;

public class XMLSerializationIO {
    Class aClass;
    private StringBuffer xmlText = null;

    public void serialization(Object object, String fileName) {
        xmlText = start();
        aClass = object.getClass();
        xmlText.append("<" + object.getClass().getName() + "> \n");
        for (Field field:object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                xmlText.append("<" + field.getName() +
                                 " type = \"" + field.getType().getSimpleName() + "\"" +
                                 " value = \"" + ((field.get(object) == null)? "" : field.get(object)) +"\"/> \n");
//                 + + field.get(home)
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        xmlText.append("</" + object.getClass().getName() + "> \n");
        writeFile(fileName);
    }

    public Object deSerialization(String fileName) {
        String stringBuffer = readFile(fileName);
        Object object = null;
        int i = 0;
        int lastIndex = stringBuffer.lastIndexOf("\n");
        String[] lines = stringBuffer.split("\n");
        for (String line : lines) {
            if (i == 1) {
                try {
                    Class clazz = Class.forName(line.substring(1, line.length()-2));
                    object = clazz.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            if (i > 1 & i < lines.length-1) {
                String pasreField = line.substring(1, line.indexOf(" "));
                String parseType = line.substring(line.indexOf("type = \"")+8, line.indexOf("\"", line.indexOf("type = \"")+8));
                String parseValue = line.substring(line.indexOf("value = \"")+9, line.indexOf("\"", line.indexOf("value = \"")+9));
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getName().equals(pasreField)) {
                        try {
                            switch (field.getType().getSimpleName()) {
                                case "byte" : field.set(object, Integer.parseInt(parseValue)); break;
                                case "short" : field.set(object, Short.parseShort(parseValue)); break;
                                case "char" : field.set(object, (char) Integer.parseInt(parseValue)); break;
                                case "int" : field.set(object, Integer.parseInt(parseValue)); break;
                                case "long" : field.set(object, Long.parseLong(parseValue)); break;
                                case "float" : field.set(object, Float.parseFloat(parseValue)); break;
                                case "double" : field.set(object, Double.parseDouble(parseValue)); break;
                                case "boolean" : field.set(object, Boolean.parseBoolean(parseValue)); break;
                                case "String" : field.set(object, parseValue); break;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            i++;
        }
        return object;
    }
    public StringBuffer start () {
        String startXMLText = null;
        startXMLText = "<?xml version=1.0 encoding=UTF-8?> " +
                "<java version=" + System.getProperty("java.version") + "> \n";
        return new StringBuffer().append(startXMLText);
    }



    private void writeFile(String fileName) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(xmlText.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName){
        int i;
        String text = "";
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            while ((i = fileInputStream.read()) != -1) {
                text +=((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
