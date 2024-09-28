package com.github.bgomar.bgconsolelogger.tools;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.iban.BbanStructure;
import fr.marcwrobel.jbanking.iban.RandomIban;
import net.datafaker.Faker;
import net.datafaker.providers.base.Address;
import net.datafaker.providers.base.Ancient;
import net.datafaker.providers.base.Animal;
import net.datafaker.providers.base.Appliance;
import net.datafaker.providers.base.Artist;
import net.datafaker.providers.base.Barcode;
import net.datafaker.providers.base.Company;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.Job;
import net.datafaker.providers.base.Music;
import net.datafaker.providers.base.Twitter;
import net.datafaker.providers.base.University;
import net.datafaker.providers.entertainment.Movie;
import net.datafaker.providers.entertainment.Pokemon;
import net.datafaker.providers.entertainment.RickAndMorty;
import net.datafaker.providers.food.Food;
import net.datafaker.providers.videogame.SuperMario;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DataFakerTools {

    public static final List<String> FAKER_LOCALES = List.of(
        "en",
        "en-CA",
        "en-US",
        "nl",
        "uk");

    public static final List<String> FAKER_GENERATORS = List.of(
        "Address",
        "Ancient god",
        "Ancient hero",
        "Ancient primordial",
        "Ancient titan",
        "Animal",
        "Appliance brand",
        "Appliance equipment",
        "Artist",
        "Banking - IBAN",
        "Bar code",
        "Company buzzword",
        "Company name",
        "Food",
        "Id number",
        "Job key skill",
        "Job position",
        "Job title",
        "Movie quote",
        "Music instrument",
        "Pokemon location",
        "Pokemon name",
        "Super Mario character",
        "Super Mario game",
        "Rick and Morty character",
        "Rick and Morty quote",
        "Twitter user id",
        "Twitter username",
        "University name");

    private static LinkedHashSet<String> limitMaxEntries(LinkedHashSet<String> set, int howMany) {
        if (set.size() > howMany) {
            return set.stream().limit(howMany).collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return set;
    }

    private static LinkedHashSet<String> sort(LinkedHashSet<String> set) {
        return set.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static String generateFakeData(String generator, String locale, int howMany) {
        if (howMany < 1) {
            return "";
        }
        try {
            int howManyPlusExtra = howMany * 3;
            Faker faker = new Faker(Locale.forLanguageTag(locale));
            LinkedHashSet<String> lines = new LinkedHashSet<>();
            boolean needToLimitResultSize = true;
            boolean needToSortResult = true;

            switch (generator) {
                case "Address" -> {
                    Address address = faker.address();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(address.fullAddress());
                    }
                }
                case "Ancient god" -> {
                    Ancient ancient = faker.ancient();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(ancient.god());
                    }
                }
                case "Ancient hero" -> {
                    Ancient ancient = faker.ancient();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(ancient.hero());
                    }
                }
                case "Ancient titan" -> {
                    Ancient ancient = faker.ancient();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(ancient.titan());
                    }
                }
                case "Ancient primordial" -> {
                    Ancient ancient = faker.ancient();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(ancient.primordial());
                    }
                }
                case "Animal" -> {
                    Animal animal = faker.animal();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(animal.name());
                    }
                }
                case "Appliance brand" -> {
                    Appliance appliance = faker.appliance();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(appliance.brand());
                    }
                }
                case "Appliance equipment" -> {
                    Appliance appliance = faker.appliance();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(appliance.equipment());
                    }
                }
                case "Artist" -> {
                    Artist artist = faker.artist();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(artist.name());
                    }
                }
                case "Banking - IBAN" -> {
                    needToLimitResultSize = false;
                    needToSortResult = false;
                    String country = locale.toUpperCase();
                    if (country.contains("-")) {
                        country = country.substring(0, country.indexOf("-"));
                    }
                    IsoCountry isoCountry;
                    try {
                        isoCountry = IsoCountry.valueOf(country);
                    } catch (IllegalArgumentException e) {
                        isoCountry = IsoCountry.FR;
                    }
                    BbanStructure bbanStructure = BbanStructure.forCountry(isoCountry).orElse(BbanStructure.FR);
                    lines.add("=== " + bbanStructure + " IBAN codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(new RandomIban().next(bbanStructure).toPrintableString());
                    }
                }
                case "Bar code" -> {
                    needToLimitResultSize = false;
                    needToSortResult = false;
                    Barcode barcode = faker.barcode();
                    lines.add("=== ean8 bar codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(Long.toString(barcode.ean8()));
                    }
                    lines.add("\n=== ean13 bar codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(Long.toString(barcode.ean13()));
                    }
                    lines.add("\n=== gtin8 bar codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(Long.toString(barcode.gtin8()));
                    }
                    lines.add("\n=== gtin12 bar codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(Long.toString(barcode.gtin12()));
                    }
                    lines.add("\n=== gtin13 bar codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(Long.toString(barcode.gtin13()));
                    }
                    lines.add("\n=== gtin14 bar codes ===");
                    for (int i = 0; i < howMany; i++) {
                        lines.add(Long.toString(barcode.gtin14()));
                    }
                }
                case "Company name" -> {
                    Company company = faker.company();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(company.name());
                    }
                }
                case "Company buzzword" -> {
                    Company company = faker.company();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(company.buzzword());
                    }
                }
                case "Food" -> {
                    Food food = faker.food();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(food.ingredient());
                    }
                }
                case "Id number" -> {
                    IdNumber idNumber = faker.idNumber();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(idNumber.valid());
                    }
                }
                case "Job title" -> {
                    Job job = faker.job();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(job.title());
                    }
                }
                case "Job position" -> {
                    Job job = faker.job();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(job.position());
                    }
                }
                case "Job key skill" -> {
                    Job job = faker.job();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(job.keySkills());
                    }
                }
                case "Movie quote" -> {
                    Movie movie = faker.movie();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(movie.quote());
                    }
                }
                case "Music instrument" -> {
                    Music music = faker.music();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(music.instrument());
                    }
                }
                case "Pokemon location" -> {
                    Pokemon pokemon = faker.pokemon();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(pokemon.location());
                    }
                }
                case "Pokemon name" -> {
                    Pokemon pokemon = faker.pokemon();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(pokemon.name());
                    }
                }
                case "Super Mario game" -> {
                    SuperMario superMario = faker.superMario();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(superMario.games());
                    }
                }
                case "Super Mario character" -> {
                    SuperMario superMario = faker.superMario();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(superMario.characters());
                    }
                }
                case "Rick and Morty character" -> {
                    RickAndMorty rickAndMorty = faker.rickAndMorty();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(rickAndMorty.character());
                    }
                }
                case "Rick and Morty quote" -> {
                    RickAndMorty rickAndMorty = faker.rickAndMorty();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(rickAndMorty.quote());
                    }
                }
                case "Twitter username" -> {
                    Twitter twitter = faker.twitter();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(twitter.userName());
                    }
                }
                case "Twitter user id" -> {
                    Twitter twitter = faker.twitter();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(twitter.userId());
                    }
                }
                case "University name" -> {
                    University university = faker.university();
                    for (int i = 0; i < howManyPlusExtra; i++) {
                        lines.add(university.name());
                    }
                }
            }

            if (needToLimitResultSize) {
                lines = limitMaxEntries(lines, howMany);
            }
            if (needToSortResult) {
                lines = sort(lines);
            }

            StringBuilder sb = new StringBuilder();
            lines.forEach(s -> sb.append(s).append("\n"));
            return sb.toString().trim();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}