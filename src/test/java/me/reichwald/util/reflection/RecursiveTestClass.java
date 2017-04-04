package me.reichwald.util.reflection;

public class RecursiveTestClass
{
  
  InnerType inner = new InnerType();
  
  public InnerType getInnerType()
  {
    return inner;
  }
  
  
  public static class InnerType
  {
    
    InnerInnerType anon = new InnerInnerType(){
      
      @Override
      public String getStuff()
      {
        return super.getStuff() + "Things";
      }
    };
    
    public static class InnerInnerType {
      
      String bla = "blubb";
      
      public String getStuff()
      {
        return bla;
      }
      
    }
    
    
  }
  
  
}
