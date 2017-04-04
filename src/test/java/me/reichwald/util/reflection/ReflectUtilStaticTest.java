package me.reichwald.util.reflection;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lombok.val;

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ReflectUtilStaticTest extends ReflectUtilTest
{

  
  
  @Test
  public void test1GetClass()
  {
    assertEquals( Integer.class, ReflectUtil.getClass( "java.lang.Integer" ).unpack() );
    
  }
  
  @Test
  public void test2GetAndSetField()
  {
    val testString = "This is a Test";
    val testInt = 2;
    val testInteger = Integer.valueOf( 3 );
    
    val cls = ReflectUtil.getClass( TestClass.class );
    
    assertEquals( testString, cls.<String>getField( "someStaticString" ).setValue( testString ).getValue() );
    assertEquals( Integer.valueOf( testInt ), cls.<Integer>getField( "someStaticInt" ).setValue( testInt ).getValue() );
    assertEquals( testInteger, cls.<Integer>getField( "someStaticInteger" ).setValue( testInteger ).getValue() );
    
  }
  
  @Test
  public void test3InvokeMethod()
  {
    val testString = "This is a Test";
    
    val cls = ReflectUtil.getClass( TestClass.class );
    
    cls.<Void>getMethod( "setSomeStaticString", String.class ).invoke( testString );
    
    val result = cls.<String>getMethod( "getSomeStaticString" ).invoke().unpack();
    
    assertEquals( testString, result );
  }
}
