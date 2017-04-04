package me.reichwald.util.reflection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lombok.val;

import static org.junit.Assert.*;


@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ReflectUtilInstanceTest extends ReflectUtilTest
{
  
  
  
  @Test
  public void test1GetInstance()
  {
    val object = getTestObject();
    
    val wrapped = ReflectUtil.getInstance( object );
    
    assertEquals( TestClass.class, wrapped.getInstanceClass().unpack() );
    assertEquals( object, wrapped.unpack() );
    
    
  }
  
  @Test
  public void test2GetFieldValue()
  {
    val object = getTestObject();
    
    val f1 = ReflectUtil.getInstance( object ).getField( "someString" );
    assertNotNull( f1.unpack() );
    
    assertEquals( TEST_STRING, f1.getValue() );
    
  }
  
  @Test
  public void test3SetFieldValue()
  {
    val newString = "Blabba Blubb";
    val object = getTestObject();
    
    val f1 = ReflectUtil.getInstance( object ).getField( "someString" );
    
    f1.setValue( newString );
    
    assertEquals( object.getSomeString(), newString );
    
  }
  
  @Test
  public void test4CallMethod()
  {
    val newString = "Blabba Blubb";
    val object = getTestObject();
    
    val setter = ReflectUtil.getInstance( object ).getMethod( "setSomeString", String.class );
    val getter = ReflectUtil.getInstance( object ).<String>getMethod( "getSomeString" );
    
    
    
    setter.invoke( newString );
    
    assertEquals( getter.invoke().getInstanceClass().unpack(), String.class );
    assertEquals( getter.invoke().unpack(), object.getSomeString() );
    assertEquals( getter.invokeRaw(), newString );
    
  }
  
  @Test
  public void test5InnerAndAnonTypes()
  {
    val object = new RecursiveTestClass();
    
    val wrapped = ReflectUtil.getInstance( object );
    val inner = wrapped.<RecursiveTestClass.InnerType>getMethod( "getInnerType" ).invoke();
    val anon = inner.<RecursiveTestClass.InnerType.InnerInnerType>getField( "anon" ).getValueWrapped();
    
    val blubb = anon.<String>getField( "bla" ).getValue();
    
    assertEquals( "blubb", blubb );
    
    val str = anon.<String>getMethod( "getStuff" ).invokeRaw();
    
    assertEquals( "blubbThings", str );
    
    assertNotEquals( anon.getMethod( "getStuff" ).unpack(), anon.asSuperClassInstance().getMethod( "getStuff" ) );
    
  }
  
  @Test
  public void test6CreateInstance()
  {
    val cls = ReflectUtil.getClass( TestClass.class );
    
    val instance = cls.getConstructor().createInstance();
    
    instance.<Void>getMethod( "setSomeInteger", Integer.class ).invoke( 5 );
    
    assertEquals( Integer.valueOf( 5 ), instance.unpack().getSomeInteger() );
  }
  
  
  

}
