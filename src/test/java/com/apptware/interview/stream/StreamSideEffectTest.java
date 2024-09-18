package com.apptware.interview.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StreamSideEffectTest {

  @Test
  void parallelStream() {
    List<Integer> numbers = new ArrayList<>();
      ConcurrentLinkedQueue<Integer> results = new ConcurrentLinkedQueue<>();

    IntStream.range(1, 100000).forEach(numbers::add);
    // DO NOT CHANGE >>>>>
    numbers.parallelStream()
        // <<<<< DO NOT CHANGE
        .map(
            number -> {
              results.add(number * 2);
              return number * 2;
            });

      Assertions.assertThat(results).containsExactlyInAnyOrder(numbers.stream().map(n -> n * 2).toArray(Integer[]::new));
  }
}
