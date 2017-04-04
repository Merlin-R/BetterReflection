package me.reichwald.util.reflection;

public class ReflectInstance<T> implements IReflectWrapper<T>
{
  
  T wrapped;
  ReflectClass<T> cls;
  
  
  ReflectInstance( T instance, ReflectClass<T> cls )
  {
    this.wrapped = instance;
    this.cls = cls;
  }
  
  @Override
  public T unpack()
  {
    return wrapped;
  }
  
  public ReflectClass<T> getInstanceClass()
  {
    return cls;
  }
  
  public Class<T> getInstanceClassRaw()
  {
    return cls.unpack();
  }
  
  @SuppressWarnings( { "rawtypes", "unchecked" } )
  public ReflectInstance<?> asSuperClassInstance()
  {
    return new ReflectInstance( wrapped, cls.getSuperClass() );
  }
  
  @SuppressWarnings( { "rawtypes", "unchecked" } )
  public <T2> ReflectField<T2> getField( String fieldName )
  {
    return (ReflectField<T2>)new ReflectField( cls.getFieldRaw( fieldName ), this );
  }

  @SuppressWarnings( { "rawtypes", "unchecked" } )
  public <T2> ReflectMethod<T2> getMethod( String methodName, Class<?>...parameterTypes )
  {
    return (ReflectMethod<T2>)new ReflectMethod( cls.getMethodRaw( methodName, parameterTypes ), this );
  }

}
