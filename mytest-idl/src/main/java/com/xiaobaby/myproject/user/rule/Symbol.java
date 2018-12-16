/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.xiaobaby.myproject.user.rule;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum Symbol implements org.apache.thrift.TEnum {
  GREATER_THAN(0),
  LESS_THAN(1),
  EQUAL(2),
  NOT_EQUAL(3),
  GREATER_THAN_EQUAL(4),
  LESS_THAN_EQUAL(5),
  IN(6),
  BETWEEN(7);

  private final int value;

  private Symbol(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static Symbol findByValue(int value) { 
    switch (value) {
      case 0:
        return GREATER_THAN;
      case 1:
        return LESS_THAN;
      case 2:
        return EQUAL;
      case 3:
        return NOT_EQUAL;
      case 4:
        return GREATER_THAN_EQUAL;
      case 5:
        return LESS_THAN_EQUAL;
      case 6:
        return IN;
      case 7:
        return BETWEEN;
      default:
        return null;
    }
  }
}