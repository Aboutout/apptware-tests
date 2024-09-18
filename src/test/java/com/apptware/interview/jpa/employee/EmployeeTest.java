package com.apptware.interview.jpa.employee;

import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * This test class has a validation for {@link com.apptware.interview.jpa.employee.Employee}. This
 * test verifies the save operation for the Employee entity. It creates an Employee object, saves it
 * using the EmployeeRepository, and then retrieves the stored Employee object to ensure the
 * integrity of its parameters.
 *
 * <p>The candidate is expected **NOT** to modify the test case but the corresponding class for
 * which the test case is written.
 */
@DataJpaTest
class EmployeeTest {

  @Autowired private EmployeeRepository employeeRepository;

  @Test
  void testSaveEmployee() {
    UUID employeeId = UUID.randomUUID();
    String employeeName = "Firstname Lastname";

    Employee employee = new Employee();
    employee.setId(employeeId);
    employee.setName(employeeName);

    employeeRepository.save(employee);
    //checking nnot null first
    Assertions.assertThat(retrievedEmployee)
            .isNotNull()
            .extracting(Employee::getId, Employee::getName)
            .containsExactly(employeeId, employeeName);
  }
}
