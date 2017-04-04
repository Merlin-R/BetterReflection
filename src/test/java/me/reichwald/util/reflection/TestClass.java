package me.reichwald.util.reflection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TestClass
{
  
  @Getter @Setter
  static String someStaticString;
  @Getter @Setter
  static Integer someStaticInteger;
  @Getter @Setter
  static int someStaticInt;
  
  
  String  someString;
  Integer someInteger;
  int     someInt;

}
