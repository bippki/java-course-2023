package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {
    private Task(){

    }

    public static List<Animal> sortHeight(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortWeight(Collection<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> countAnimalType(Collection<Animal> animals) {
        Map<Animal.Type, Integer> typeCountMap = new EnumMap<>(Animal.Type.class);

        for (Animal animal : animals) {
            typeCountMap.merge(animal.type(), 1, Integer::sum);
        }
        return typeCountMap;
    }

    public static Animal getAnimalLongName(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex findMoreCommonSex(Collection<Animal> animals) {
        Map<Animal.Sex, Long> countMap = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        return countMap.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> findHeavyType(Collection<Animal> animals) {
        Map<Animal.Type, Animal> fatty;

        fatty = animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));

        return fatty;
    }

    public static Animal findOld(Collection<Animal> animals, int k) {
        if (k <= 0 || animals.isEmpty()) {
            return null;
        }

        Comparator<Animal> compByAgeReversed = Comparator.comparing(Animal::age).reversed();

        return animals.stream()
            .sorted(compByAgeReversed)
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }


    public static Optional<Animal> findHeavyHeight(Collection<Animal> animals, int k) {
        if (k <= 0) {
            return null;
        }
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static int sumPaws(Collection<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> findAgeAndPaws(Collection<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> detectDanger(Collection<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .collect(Collectors.toList());
    }

    public static int countWGreaterH(Collection<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> findComplexNames(Collection<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static boolean hasDogTallerThanK(Collection<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> calculateTotalWeightByType(Collection<Animal> animals, int minAge, int maxAge) {
        if (maxAge < 0 || minAge < 0) {
            return null;
        }
        return animals.stream()
            .filter(animal -> animal.age() >= minAge && animal.age() <= maxAge)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortAnimalTypeSexName(Collection<Animal> animals) {
        Comparator<Animal> typeSort = Comparator.comparing(Animal::type);
        Comparator<Animal> sexSort = Comparator.comparing(Animal::sex);
        Comparator<Animal> nameSort = Comparator.comparing(Animal::name);

        return animals.stream()
            .sorted(typeSort.thenComparing(sexSort).thenComparing(nameSort))
            .collect(Collectors.toList());
    }

    public static boolean doSpidersBiteMoreThanDogs(Collection<Animal> animals) {
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        return spiderBites > dogBites;
    }

    public static Optional<Animal> findHeavyFish(List<Animal>... animalLists) {
        return List.of(animalLists).stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Map<String, Set<ValidationError>> inputManager(Collection<Animal> animals) {
        Map<String, Set<ValidationError>> invalidTypes = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationError> errors = new HashSet<>();

            if (animal.name() == null) {
                errors.add(new ValidationError("name", "is null"));
            } else if (animal.name().isEmpty()) {
                errors.add(new ValidationError("name", "is empty"));
            }

            if (animal.type() == null) {
                errors.add(new ValidationError("type", "is null"));
            }

            if (animal.sex() == null) {
                errors.add(new ValidationError("sex", "is null"));
            }

            if (animal.age() < 0) {
                errors.add(new ValidationError("age", "is negative"));
            }

            if (animal.height() <= 0) {
                errors.add(new ValidationError("height", "is not positive"));
            }

            if (animal.weight() <= 0) {
                errors.add(new ValidationError("weight", "is not positive"));
            }

            if (!errors.isEmpty()) {
                invalidTypes.put(animal.name(), errors);
            }
        }

        return invalidTypes;
    }

    public static Map<String, String> CozyPrinter(Collection<Animal> animals) {
        Map<String, Set<ValidationError>> res = inputManager(animals);
        return res.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                a -> a.getValue().stream()
                    .map(error -> error.getValue() + " " + error.getDesc())
                    .collect(Collectors.joining(", "))
            ));
    }

}
