package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String animal = "Cat";
        char gender = 'F';
        int paws = 4;
        boolean isVaccinated = true;
        short weightInKilos = 12;
        long hairAmount = 922_330_000L;
        byte foodPortionsPerDay = 3;
        double foodPortionInGrams = 50.25855;
        float sleepHoursPerWeek = 140.999F;
        LOG.debug("Animal info :\n"
                + " animal type : {}, male or female : {}, has vaccine : {}, number of paws : {},"
                + " weights : {} kg, has {} hairs, should eat a {}g portion {} times a day"
                + " and sleep {} hrs per week.", animal, gender, isVaccinated, paws, weightInKilos,
                hairAmount, foodPortionInGrams, foodPortionsPerDay, sleepHoursPerWeek);
    }
}