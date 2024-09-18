package com.apptware.interview.comparison;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This test class has a validation for {@link com.apptware.interview.comparison.SomeClass}. Creates
 * two lists of SomeClass instances using getSomeClasses(). Combines the lists using
 * Stream.concat(). Sorts the combined stream by lastInvoked in descending order. Removes duplicates
 * using distinct(). Converts the result back to a list and asserts its size is 10 as the
 * getSomeClasses() always generates 10 items with the same ID.
 *
 * <p>The candidate is expected **NOT** to modify the test case but the corresponding class for
 * which the test case is written.
 */
class ComparisonTest {

  public static final Random RANDOM = new Random();

  @Test
  void testSomeClasses() {
    List<SomeClass> list1 = getSomeClasses();
    List<SomeClass> list2 = getSomeClasses();

    List<SomeClass> latestDistinctItems =
        Stream.concat(list1.stream(), list2.stream())
            .sorted(Comparator.comparing(SomeClass::getLastInvoked).reversed())
            .distinct()
            .toList();
    Assertions.assertThat(latestDistinctItems)
            .hasSize(20)
            .isSortedAccordingTo(Comparator.comparing(SomeClass::getLastInvoked).reversed());
  }

  // Generates the same 10 unique instances everytime this method is invoked
  private static List<SomeClass> getSomeClasses() {

    List<SomeClass> someClasses = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      var someClass = new SomeClass(i, getRandomDate());
      someClasses.add(someClass);
    }

    return someClasses;
  }

  private static Date getRandomDate() {
    Calendar calendar = Calendar.getInstance();
    int year = RANDOM.nextInt(2024 - 2000) + 2000; // Random year between 2000 and 2024
    int dayOfYear =
        RANDOM.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_YEAR))
            + 1; // Random day of the year
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
    return calendar.getTime();
  }
}
