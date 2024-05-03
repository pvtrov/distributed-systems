package org.agh.edu.pl.conditions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.agh.edu.pl.gen.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor
public class GreenhouseConditions {
    private final Random random = new Random();

    public Conditions createConditionsForGreenhouse() {
        return Conditions.newBuilder()
                .setTemperature(random.nextFloat() * 35.0f + 5.0f)
                .setHumidity(random.nextFloat() * 100.0f)
                .setIsDaytime(random.nextBoolean())
                .addAllLight(generateLightConditions())
                .addAllSoil(generateSoilConditions())
                .setCo2Level(random.nextFloat() * 2000.0f)
                .setNitrogenLevel(random.nextFloat() * 100.0f)
                .setPlantType(PlantType.values()[random.nextInt(PlantType.values().length - 1)])
                .setFertilizerType(FertilizerType.values()[random.nextInt(FertilizerType.values().length - 1)])
                .build();
    }

    private List<Light> generateLightConditions() {
        return IntStream.range(0, 3)
                .mapToObj(i -> Light.newBuilder()
                        .setIntensity(random.nextFloat() * 100.0f)
                        .setColor(Color.values()[random.nextInt(Color.values().length - 1)])
                        .build())
                .collect(Collectors.toList());
    }

    private List<Soil> generateSoilConditions() {
        return IntStream.range(0, 2) // Assume 2 soil sensors per greenhouse
                .mapToObj(i -> Soil.newBuilder()
                        .setMoistureLevel(random.nextFloat() * 100.0f)
                        .setPHLevel(random.nextFloat() * 14.0f)
                        .build())
                .collect(Collectors.toList());
    }
}
