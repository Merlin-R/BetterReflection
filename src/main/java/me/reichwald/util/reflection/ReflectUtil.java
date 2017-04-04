package me.reichwald.util.reflection;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMapBuilder;
import com.google.common.collect.Maps;

public class ReflectUtil
{
  private static final BiMap<Class<?>,Class<?>> TO_PRIMITIVE = new ImmutableBiMapBuilder<Class<?>,Class<?>>()
      .put( Boolean.class, boolean.class )
      .put( Byte.class, byte.class )
      .put( Short.class, short.class )
      .put( Integer.class, int.class )
      .put( Long.class, long.class )
      .put( Float.class, float.class )
      .put( Double.class, double.class )
      .put( Character.class, char.class )
      .getBiMap();
  
  private static final Map<String, ReflectClass<?>> CLASSES = Maps.newHashMap();
  

  
  public static final ReflectClass<?> getClass( String cls, boolean forceReload )
  {
    
    if ( CLASSES.containsKey( cls ) && !forceReload )
      return CLASSES.get( cls );
    
    try
    {
      Class<?> wrapped = Class.forName( cls );
      
      @SuppressWarnings({ "rawtypes","unchecked" })
      ReflectClass<?> wrapper =  new ReflectClass( wrapped );
      CLASSES.put( cls, wrapper );
      return wrapper;
      
    }
    catch ( ClassNotFoundException e )
    {
      return null;
    }
  }
  
  public static ReflectClass<?> getClass( String cls )
  {
    return getClass( cls, false );
  }
  
  @SuppressWarnings( "unchecked" )
  public static <T> ReflectClass<T> getClass( Class<T> cls )
  {
    return (ReflectClass<T>) getClass( cls.getName() );
  }
  
  @SuppressWarnings( "unchecked" )
  public static <T> ReflectInstance<T> getInstance( T object )
  {
    if ( object == null ) return new ReflectInstance<T>( null, null );
    return new ReflectInstance<T>( object, new ReflectClass<T>( (Class<T>)object.getClass() ) );
  }
  
  public static final Object[] unpack( IReflectWrapper<?>...objects )
  {
    return Arrays.stream( objects ).map( IReflectWrapper :: unpack ).collect( Collectors.toList() ).toArray();
  }
  
  public static final Object[] unpackIfWrapped( Object...objects )
  {
    return Arrays.stream( objects ).map( ReflectUtil :: unpackIfWrapped ).collect( Collectors.toList() ).toArray();
  }
  
  public static final Object unpackIfWrapped( Object object )
  {
    if ( object instanceof IReflectWrapper )
      return ((IReflectWrapper<?>) object).unpack();
    return object;
  }

  
}
