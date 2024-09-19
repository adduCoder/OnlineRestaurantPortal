package com.restaurants.dto;

import lombok.Data;

@Data
public class RestaurantOutDto {
  /**
   * Unique identifier for the restaurant.
   */
  private Integer id;
  /**
   * Identifier of the user who owns the restaurant.
   */
  private Integer userId;
  /**
   * Name of the restaurant.
   */
  private String restaurantName;
  /**
   * Address of the restaurant.
   */
  private String address;
  /**
   * Contact number of the restaurant.
   */
  private String contactNumber;

  /**
   * Description of the restaurant.
   */
  private String description;
  /**
   * URL of the restaurant's image.
   */
  private byte[] imageData;

  /**
   * Retrieves the image data of the restaurant.
   *
   * <p>
   * The method returns a clone of the `imageData` array to ensure the internal
   * state of the object remains unchanged. This prevents unintended modifications
   * to the original array.
   * </p>
   *
   * @return a byte array representing the image data of the restaurant,
   * or {@code null} if no image data is available.
   */
  public byte[] getImageData() {
    return (imageData != null) ? imageData.clone() : null; // Returning a copy to protect the internal array
  }

  /**
   * Sets the image data for the restaurant.
   *
   * <p>
   * A clone of the provided byte array is stored to protect the internal
   * state of the object, ensuring that external changes to the input array
   * do not affect the stored data.
   * </p>
   *
   * @param imageData a byte array representing the image data of the restaurant,
   * or {@code null} if no image data is being set.
   */
  public void setImageData(final byte[] imageData) {
    this.imageData = (imageData != null) ? imageData.clone() : null; // Storing a copy to avoid external changes
  }



}
