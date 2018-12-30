// Copyright (c) Keith D Gregory, all rights reserved
package com.venu;

import java.nio.ByteBuffer;


/**
 *  A program that allocates a large buffer outside the Java heap.
 */
public class Allocator
{
    public static void main(String[] argv)
            throws Exception
    {
        for (int i= 0; i<5; i++ ){

        ByteBuffer myBuffer = ByteBuffer.allocateDirect(100 * 1024 * 1024);
    }
        // now that we've got the buffer, we'll sleep so that you can see
        // it in pmap(): look for a 102408K "anon" block with permissions "rwx"
        Thread.sleep(600000L);
    }
}