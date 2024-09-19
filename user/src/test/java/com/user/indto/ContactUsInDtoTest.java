package com.user.indto;


import com.user.dto.ContactUsInDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ContactUsInDtoTest {

  @Test
  public void testDefaultConstructor() {
    // Arrange
    ContactUsInDto contactUsInDto = new ContactUsInDto();

    // Assert
    assertThat(contactUsInDto.getName()).isNull();
    assertThat(contactUsInDto.getEmails()).isNull();
    assertThat(contactUsInDto.getSubject()).isNull();
    assertThat(contactUsInDto.getMessage()).isNull();
  }

  @Test
  public void testAllArgsConstructor() {
    // Arrange
    String name = "John Doe";
    List<String> emails = Arrays.asList("john.doe@example.com", "support@example.com");
    String subject = "Inquiry about product";
    String message = "I would like to know more about the product details.";

    // Act
    ContactUsInDto contactUsInDto = new ContactUsInDto(name, emails, subject, message);

    // Assert
    assertThat(contactUsInDto.getName()).isEqualTo(name);
    assertThat(contactUsInDto.getEmails()).isEqualTo(emails);
    assertThat(contactUsInDto.getSubject()).isEqualTo(subject);
    assertThat(contactUsInDto.getMessage()).isEqualTo(message);
  }

  @Test
  public void testSettersAndGetters() {
    // Arrange
    ContactUsInDto contactUsInDto = new ContactUsInDto();
    String name = "Jane Smith";
    List<String> emails = Collections.singletonList("jane.smith@example.com");
    String subject = "Feedback";
    String message = "This is some feedback.";

    // Act
    contactUsInDto.setName(name);
    contactUsInDto.setSubject(subject);
    contactUsInDto.setMessage(message);

    // Assert
    assertThat(contactUsInDto.getName()).isEqualTo(name);
    assertThat(contactUsInDto.getSubject()).isEqualTo(subject);
    assertThat(contactUsInDto.getMessage()).isEqualTo(message);
  }
}
