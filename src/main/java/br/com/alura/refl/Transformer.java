package br.com.alura.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformer {

    private Boolean validate(Field sourceField, Field targetField) {
        System.out.println("Aqui o source field");
        System.out.println(sourceField);
        System.out.println("Aqui o targetfield");
        System.out.println(targetField);
        if(sourceField.getName().equals(targetField.getName())) {
            if(sourceField.getType().equals(targetField.getType())) {
                sourceField.setAccessible(true);
                targetField.setAccessible(true);
                return true;
            }
        }
        return false;
    }

    //O <I, O> é um Generics
    //O Reflection deve ser usado apenas em momentos específicos porque ele tem uma lentidão maior.
    public <Inp, Out> Out transformToDTO(Inp input) throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        Class<?> source = input.getClass();
        Class<?> classDTO = Class.forName(source.getName() + "DTO");

        Out targetClass = (Out) classDTO.getDeclaredConstructor().newInstance();


        Field[] sourceFields = source.getDeclaredFields();
        Field[] classDTOFields = classDTO.getDeclaredFields();

        Arrays.stream(sourceFields).forEach(sourceField ->
                Arrays.stream(classDTOFields).forEach(classDTOField -> {
                    if(validate(sourceField, classDTOField)) {
                        try {
                            classDTOField.set(targetClass, sourceField.get(input));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    };
                }));

        return targetClass;

    }
}

