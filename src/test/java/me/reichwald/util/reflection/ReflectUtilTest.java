package me.reichwald.util.reflection;

public class ReflectUtilTest
{
  public static final String TEST_STRING = "TestString";
  public static final Integer TEST_INTEGER = Integer.valueOf( 5 );
  public static final int TEST_INT = 20;
  
  TestClass getTestObject()
  {
    TestClass test = new TestClass();
    
    test.setSomeString( TEST_STRING );
    test.setSomeInt( TEST_INT );
    test.setSomeInteger( TEST_INTEGER );
    
    return test;
  }
}
