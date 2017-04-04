package me.reichwald.util.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectConstructor<T> implements IReflectWrapper<Constructor<T>>
{
  Constructor<T> wrapped;
  ReflectClass<T> cls;
  
  public ReflectConstructor( Constructor<T> wrapped, ReflectClass<T> cls )
  {
    this.wrapped = wrapped;
    this.cls = cls;
  }
  
  public ReflectConstructor( Constructor<T> wrapped, Class<T> cls )
  {
    this( wrapped, ReflectUtil.getClass( cls ) );
  }
  
  public ReflectConstructor( Constructor<T> wrapped )
  {
    this( wrapped, wrapped.getDeclaringClass() );
  }
  
  public ReflectClass<T> getBoundClass()
  {
    return cls;
  }
  
  public Class<T> getBoundClassRaw()
  {
    return cls.unpack();
  }
  
  @Override
  public Constructor<T> unpack()
  {
    return wrapped;
  }
  
  public T createInstanceRaw( Object...params )
  {
    try
    {
      return wrapped.newInstance( params );
    }
    catch ( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e )
    {
      return null;
    }
  }
  
  public ReflectInstance<T> createInstance( Object...params )
  {
    return new ReflectInstance<T>( createInstanceRaw( params ), cls );
  }
  
  
}
