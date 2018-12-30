package com.venu;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    private final static int BYTE = 1;
    private long size;
    private long address;

    public static void main(String[] args) throws Exception{


        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);


        byte value = Byte.MIN_VALUE;
        long bytes = 1;


        long memoryAddress = unsafe.allocateMemory(bytes);

        unsafe.putAddress(memoryAddress, value); // or putByte

      //  long readValue = unsafe.getAddress(140331067723808l); // or getByte

          long readValue = unsafe.getAddress(memoryAddress); // or getByte


        // Output the value from
        System.out.println("[Byte] Reading " + readValue + " from the " + memoryAddress + " address.");




        System.out.println("memory address is " + memoryAddress);


        System.out.println("Address size is " +unsafe.addressSize());

    }

    public UnsafeTest(long size) throws NoSuchFieldException, IllegalAccessException {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public void set(long i, byte value) throws NoSuchFieldException, IllegalAccessException {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) throws NoSuchFieldException, IllegalAccessException {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }

    public void freeMemory() throws NoSuchFieldException, IllegalAccessException {
        getUnsafe().freeMemory(address);
    }
}
