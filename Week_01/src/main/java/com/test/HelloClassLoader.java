package com.test;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("com.test.Hello").newInstance();
        }catch (ClassNotFoundException e){
            System.out.println("class is not find:"+e);
        }catch (InstantiationException e){
            System.out.println("InstantiationException:"+e);
        }catch (IllegalAccessException e){
            System.out.println("IllegalAccessException:"+e);
        }
    }


}
