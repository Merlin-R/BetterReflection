package me.reichwald.util.reflection;

import java.lang.reflect.Field;

public class ReflectField<T> implements IReflectWrapper<Field>
{
  
  private final Field wrapped;
  private final ReflectClass<T> cls;
  private final ReflectInstance<T> instance;
  
  
  ReflectField( Field wrapped, Class<T> cls )
  {
    this( wrapped, ReflectUtil.getClass( cls ) );
  }
  
  ReflectField( Field wrapped, ReflectClass<T> cls )
  {
    this( wrapped, new ReflectInstance<T>( null, cls ) );
  }
  
  ReflectField( Field wrapped, ReflectInstance<T> instance )
  {
    this.wrapped = wrapped;
    this.cls = instance.getInstanceClass();
    this.instance = instance;
  }
  
  public ReflectClass<T> getBoundClass()
  {
    return cls;
  }
  
  public Class<T> getBoundClassRaw()
  {
    return cls.unpack();
  }
  
  @SuppressWarnings( "unchecked" )
  public <T2> ReflectInstance<T2> getValueWrappedAs( Class<T2> cls )
  {
    try
    {
      return new ReflectInstance<T2>( ( T2 ) wrapped.get( instance.unpack() ), ReflectUtil.getClass( cls ) );
    }
    catch ( IllegalArgumentException | IllegalAccessException e )
    {
      return null;
    }
  }
  
  @SuppressWarnings( "unchecked" )
  public ReflectInstance<T> getValueWrapped()
  {
    try
    {
      return ReflectUtil.getInstance( (T) wrapped.get( instance.unpack() ) );
    }
    catch ( IllegalArgumentException | IllegalAccessException e )
    {
      return null;
    }
  }
  
  @SuppressWarnings( "unchecked" )
  public T getValue()
  {
    try
    {
      return (T) wrapped.get( instance.unpack() );
    }
    catch ( IllegalArgumentException | IllegalAccessException e )
    {
      return null;
    }
  }
  
  @SuppressWarnings( "unchecked" )
  public <T2> T2 getValueAs( Class<T2> cls )
  {
    try
    {
      return (T2) wrapped.get( instance.unpack() );
    }
    catch ( IllegalArgumentException | IllegalAccessException e )
    {
      return null;
    }
  }
  
  @Override
  public Field unpack()
  {
    return wrapped;
  }

  public ReflectField<T> setValue( Object value )
  {
    try
    {
      wrapped.set( instance.unpack(), value );
    }
    catch ( IllegalArgumentException | IllegalAccessException e )
    {
      e.printStackTrace();
    }
    return this;
  }

}
