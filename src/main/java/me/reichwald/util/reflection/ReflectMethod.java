package me.reichwald.util.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethod<T> implements IReflectWrapper<Method>
{
  
  final Method wrapped;
  final ReflectInstance<?> instance;
  final ReflectClass<?> cls;
  
  ReflectMethod( Method wrapped, Class<T> cls )
  {
    this( wrapped, ReflectUtil.getClass( cls ) );
  }
  
  ReflectMethod( Method wrapped, ReflectClass<T> cls )
  {
    this( wrapped, new ReflectInstance<T>( null, cls ) );
  }
  
  ReflectMethod( Method wrapped, ReflectInstance<T> instance )
  {
    this.wrapped = wrapped;
    this.cls = instance.getInstanceClass();
    this.instance = instance;
  }
  
  @SuppressWarnings( "unchecked" )
  public <T2> ReflectInstance<T2> getBoundInstance()
  {
    return (ReflectInstance<T2>) instance;
  }
  
  @SuppressWarnings( "unchecked" )
  public <T2> T2 getBoundInstanceRaw()
  {
    return (T2) instance.unpack();
  }
  
  @SuppressWarnings( "unchecked" )
  public <T2> ReflectClass<T2> getBoundClass()
  {
    return (ReflectClass<T2>) cls;
  }
  
  @SuppressWarnings( "unchecked" )
  public <T2> Class<T2> getBoundClassRaw()
  {
    return (Class<T2>) cls.unpack();
  }
  
  @SuppressWarnings( "unchecked" )
  public ReflectInstance<T> invoke( Object...args )
  {
    try
    {
      return ReflectUtil.getInstance( (T) wrapped.invoke( instance.unpack(), args ) );
    }
    catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException e )
    {
      return null;
    }
  }
  
  @SuppressWarnings( "unchecked" )
  public T invokeRaw( Object...args )
  {
    try
    {
      return (T) wrapped.invoke( instance.unpack(), args );
    }
    catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException e )
    {
      return null;
    }
  }
  
  @Override
  public Method unpack()
  {
    return wrapped;
  }

  

}
