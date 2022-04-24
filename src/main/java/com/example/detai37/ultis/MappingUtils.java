package com.example.detai37.ultis;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class MappingUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass, BiFunction<S, T, T> func) {
        return source
                .stream()
                .map(element -> {
                    T result = modelMapper.map(element, targetClass);
                    func.apply(element, result);
                    return result;
                })
                .collect(Collectors.toList());
    }

    public static <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass, BiFunction<S, T, T> func) {
        return source
                .stream()
                .map(element -> {
                    T result = modelMapper.map(element, targetClass);
                    func.apply(element, result);
                    return result;
                })
                .collect(Collectors.toSet());
    }

    public static <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> {
                    T result = modelMapper.map(element, targetClass);
                    return result;
                })
                .collect(Collectors.toSet());
    }

    public static <D> D mapObject(Object source, Class<D> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public static <D> D mapObject(Object source, D des) {
        modelMapper.map(source, des);
        return des;
    }
}
