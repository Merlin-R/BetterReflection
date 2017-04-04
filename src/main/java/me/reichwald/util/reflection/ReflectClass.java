package me.reichwald.util.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectClass<T> implements IReflectWrapper<Class<T>>
{
  private final Class<T> wrapped;

  ReflectClass( Class<T> wrapped )
  {
    this.wrapped = wrapped;
  }

  @Override
  public Class<T> unpack()
  {
    return wrapped;
  }

  @SuppressWarnings( { "rawtypes", "unchecked" } )
  public <T2> ReflectField<T2> getField( String fieldName )
  {
    return ( ReflectField<T2> ) new ReflectField( getFieldRaw( fieldName ), this );
  }

  public Field getFieldRaw( String fieldName )
  {
    return getFieldRawForClass( fieldName, wrapped );
  }

  private Field getFieldRawForClass( String fieldName, Class<?> superClass )
  {
    try
    {
      Field f = superClass.getDeclaredField( fieldName );
      f.setAccessible( true );
      return f;
    }
    catch ( NoSuchFieldException | SecurityException e )
    {
      if ( superClass == Object.class ) return null;
      return getFieldRawForClass( fieldName, superClass.getSuperclass() );
    }
  }

  public Method getMethodRaw( String methodName, Class<?> ... parameterTypes )
  {
    return getMethodRawForClass( methodName, wrapped, parameterTypes );
  }

  private Method getMethodRawForClass( String methodName, Class<?> superClass, Class<?> ... parameterTypes )
  {
    try
    {
      Method m = wrapped.getDeclaredMethod( methodName, parameterTypes );
      m.setAccessible( true );
      return m;
    }
    catch ( NoSuchMethodException | SecurityException e )
    {
      if ( superClass == Object.class ) return null;
      return getMethodRawForClass( methodName, superClass.getSuperclass(), parameterTypes );
    }
  }

  @SuppressWarnings( { "unchecked", "rawtypes" } )
  public <T2> ReflectMethod<T2> getMethod( String methodName, Class<?> ... parameterTypes )
  {
    return ( ReflectMethod<T2> ) new ReflectMethod( this.getMethodRaw( methodName, parameterTypes ), this );
  }

  @SuppressWarnings( "unchecked" )
  public <T2> ReflectClass<T2> getSuperClass()
  {
    return ( ReflectClass<T2> ) ReflectUtil.getClass( wrapped.getSuperclass() );
  }

  @SuppressWarnings( { "rawtypes", "unchecked" } )
  public ReflectConstructor<T> getConstructor( Class<?> ... parameterTypes )
  {
    return ( ReflectConstructor<T> ) new ReflectConstructor( getConstructorRaw( parameterTypes ), this );
  }

  public Constructor<T> getConstructorRaw( Class<?> ... parameterTypes )
  {
    try
    {
      return wrapped.getDeclaredConstructor( parameterTypes );
    }
    catch ( NoSuchMethodException | SecurityException e )
    {
      return null;
    }
  }


}
