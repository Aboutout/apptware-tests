package com.apptware.interview.spring.beans;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// Do not change this class. The run will fail due to an error in the bean creation process that
// you're supposed to solve.
class BeanFactoryTest {

  @Autowired private BeanFactory beanFactory;

  @Test
  void getOnDemandBean() {
    String someString = "Hello World!!!";
    SomeEnum someEnumA = SomeEnum.SOME_ENUM_A;
    OnDemand onDemandBeanA = beanFactory.getOnDemandBean(someEnumA, someString);

    Assertions.assertThat(onDemandBeanA)
            .isNotNull()
            .extracting(OnDemand::getSomeString, OnDemand::getSomeEnum)
            .containsExactly(someString, someEnumA);

    String someOtherString = "Hello Other World!!!";
    SomeEnum someEnumB = SomeEnum.SOME_ENUM_B;
    OnDemand onDemandBeanB = beanFactory.getOnDemandBean(someEnumB, someOtherString);

    Assertions.assertThat(onDemandBeanB)
            .isNotNull()
            .extracting(OnDemand::getSomeString, OnDemand::getSomeEnum)
            .containsExactly(someOtherString, someEnumB);
  }
}
