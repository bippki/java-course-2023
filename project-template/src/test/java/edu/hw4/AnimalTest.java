package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static edu.hw4.Task.CozyPrinter;
import static edu.hw4.Task.countAnimalType;
import static edu.hw4.Task.findHeavyFish;
import static edu.hw4.Task.getAnimalLongName;
import static edu.hw4.Task.inputManager;
import static edu.hw4.Task.sortWeight;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {


    @Test
    public void testSortWeight() {
        Animal cat = new Animal("Simba", Animal.Type.CAT, Animal.Sex.M, 3, 40, 190, false);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 2, true);

        Collection<Animal> animals = Arrays.asList(cat, bird);

        List<Animal> sortedAnimals = sortWeight(animals, 2);

        List<Animal> expected = Arrays.asList(cat, bird);

        assertEquals(expected, sortedAnimals);
    }

    @Test
    public void testCountAnimalType() {
        Animal cat = new Animal("Simba", Animal.Type.CAT, Animal.Sex.M, 3, 40, 190, false);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 2, true);

        Collection<Animal> animals = Arrays.asList(cat, bird);

        Map<Animal.Type, Integer> typeCount = countAnimalType(animals);

        Map<Animal.Type, Integer> expected = new EnumMap<>(Animal.Type.class);
        expected.put(Animal.Type.CAT, 1);
        expected.put(Animal.Type.BIRD, 1);

        assertEquals(expected, typeCount);
    }

    @Test
    void testAnimalLongName() {
        List<Animal> inpt = List.of(
            new Animal("Hatiko", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false),
            new Animal("Paukaner", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 2, 76, 10, false)
        );
        Animal expected = inpt.get(2);
        assertEquals(expected, Task.getAnimalLongName(inpt));
    }

    @Test
    void testMoreCommonSex() {
        List<Animal> inpt = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Shurik", Animal.Type.BIRD, Animal.Sex.F, 2, 5, 1, false),
            new Animal("Musya", Animal.Type.CAT, Animal.Sex.F, 1, 25, 8, false)
        );
        assertEquals(Animal.Sex.F, Task.findMoreCommonSex(inpt));
    }

    @Test
    void testHeavyType() {
        List<Animal> inpt = List.of(
            new Animal("Mira", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Dora", Animal.Type.DOG, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Tolya", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Vova", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false)
        );
        Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.SPIDER, inpt.get(0),
            Animal.Type.DOG, inpt.get(3)
        );
        assertEquals(expected, Task.findHeavyType(inpt));
    }

    @Test
    void testOldK() {
        List<Animal> inpt = List.of(
            new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 4, 80, 12, true),
            new Animal("Doggy4", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Peter", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Vasya", Animal.Type.CAT, Animal.Sex.M, 3, 76, 10, false)
        );
        Animal expected = inpt.get(0);
        assertEquals(expected, Task.findOld(inpt, 2));
    }

    @Test
    void testHeavyHeight() {
        List<Animal> inpt = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Zhuchka", Animal.Type.DOG, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false)
        );
        Optional<Animal> expected = Optional.of(inpt.get(1));
        assertEquals(expected, Task.findHeavyHeight(inpt, 100));
    }

    @Test
    void testPaws() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false)
        );
        assertEquals(24, Task.sumPaws(given));
    }

    @Test
    void testAgeAndPaws() {
        List<Animal> given = List.of(
            new Animal("test1", Animal.Type.CAT, Animal.Sex.M, 4, 60, 10, false),
            new Animal("test2", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, true),
            new Animal("test3", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false)
        );
        assertEquals(1, Task.findAgeAndPaws(given).size());
    }

    @Test
    void testDetectDanger() {
        List<Animal> given = List.of(
            new Animal("Dogo", Animal.Type.DOG, Animal.Sex.M, 2, 123, 15, true),
            new Animal("Gosha", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Vitalii", Animal.Type.DOG, Animal.Sex.M, 4, 125, 16, true),
            new Animal("Sasha", Animal.Type.DOG, Animal.Sex.M, 3, 130, 10, false)
        );
        assertEquals(2, Task.detectDanger(given).size());
    }

    @Test
    public void testFindHeavyFish() {
        Animal goldfish = new Animal("Goldie", Animal.Type.FISH, Animal.Sex.M, 1, 5, 50, false);
        Animal shark = new Animal("Bruce", Animal.Type.FISH, Animal.Sex.M, 5, 300, 1000, false);
        Animal cat = new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 3, 40, 190, false);

        List<Animal> list1 = Arrays.asList(goldfish, cat);
        List<Animal> list2 = Arrays.asList(shark);
        List<Animal> list3 = Arrays.asList(cat, shark);

        Optional<Animal> heaviestFish = findHeavyFish(list1, list2, list3);

        assertEquals(shark, heaviestFish.orElse(null));
    }

    @Test
    public void testInputManager() {
        Animal dog = new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 60, 25,false);
        Animal cat = new Animal("Tom", Animal.Type.CAT, null, -2, 30, 10,true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, null, 2, 10, 2,true);

        Collection<Animal> animals = Arrays.asList(dog, cat, bird);

        Map<String, Set<ValidationError>> invalidTypes = inputManager(animals);

        assertFalse(invalidTypes.containsKey("Buddy"));
        assertTrue(invalidTypes.containsKey("Tweety"));

        assertEquals(2, invalidTypes.size());
        assertEquals(2, invalidTypes.get("Tom").size());
        assertEquals(1, invalidTypes.get("Tweety").size());
    }

    @Test
    public void testCozyPrinter() {
        Animal fish = new Animal("Slark", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0,false);
        Animal spider = new Animal("Arkasha", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 0,true);

        Collection<Animal> animals = Arrays.asList(fish, spider);

        Map<String, String> cozyPrinterResult = CozyPrinter(animals);

        assertTrue(cozyPrinterResult.containsKey("Slark"));
        assertTrue(cozyPrinterResult.containsKey("Arkasha"));

        assertEquals(2, cozyPrinterResult.size());
        assertEquals("weight is not positive", cozyPrinterResult.get("Slark"));
        String arkashaErrors = cozyPrinterResult.get("Arkasha");
        assertTrue(arkashaErrors.contains("weight is not positive"));
        assertTrue(arkashaErrors.contains("height is not positive"));
    }
}
