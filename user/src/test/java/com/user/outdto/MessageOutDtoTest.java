package com.user.outdto;


import com.user.dto.MessageOutDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageOutDtoTest {

  @Test
  public void testDefaultConstructor() {
    // Arrange
    MessageOutDto messageOutDto = new MessageOutDto();

    // Assert
    assertThat(messageOutDto.getMessage()).isNull();
  }

  @Test
  public void testAllArgsConstructor() {
    // Arrange
    String message = "This is a test message.";

    // Act
    MessageOutDto messageOutDto = new MessageOutDto(message);

    // Assert
    assertThat(messageOutDto.getMessage()).isEqualTo(message);
  }

  @Test
  public void testSettersAndGetters() {
    // Arrange
    MessageOutDto messageOutDto = new MessageOutDto();
    String message = "Another test message.";

    // Act
    messageOutDto.setMessage(message);

    // Assert
    assertThat(messageOutDto.getMessage()).isEqualTo(message);
  }
}

