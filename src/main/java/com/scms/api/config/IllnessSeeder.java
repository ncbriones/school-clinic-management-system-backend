package com.scms.api.config;

import com.scms.api.entity.Illness;
import com.scms.api.repository.IllnessRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IllnessSeeder implements CommandLineRunner {

    private final IllnessRepository illnessRepository;

    public IllnessSeeder(IllnessRepository illnessRepository) {
        this.illnessRepository = illnessRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> supportedIllness = List.of(
                "Fever",
                "Cough",
                "Headache",
                "Stomach ache",
                "Diarrhea",
                "Tooth ache",
                "Acid reflux",
                "Menstrual cramps",
                "Sore throat",
                "Muscle ache",
                "Asthma"
        );

        for (String illness: supportedIllness) {
            if (!illnessRepository.existsByName(illness)) {
                illnessRepository.save(new Illness(illness));
                System.out.println("Successfully added illness: " + illness);
            }
        }
        System.out.println("Illness seeder completed!");
    }
}
