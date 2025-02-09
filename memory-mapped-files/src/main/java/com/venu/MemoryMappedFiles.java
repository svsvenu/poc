package com.venu;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.CREATE;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class MemoryMappedFiles {

    private static FileChannel fc = null;
    private static String fileName ;
    private static Long memFileRollOverSize = 1093l;
    private static MappedByteBuffer mbb = null;
    private static Long currentPosition = 0l;

    public static void main(String[] args) throws Exception{
        fileName = args[0];
        if (args[1].equalsIgnoreCase("mem")) {
            for (int i=0; i<10000; i++ ) {
                String content= getContent();
                writeToFile(content + "adfadsfasdfasdfasfadfasdfasdfasdfafd");
               // currentPosition += content.length();
                System.out.println("writttne to file");
                 Thread.sleep(100);
            }

        }
        else {
            Path filePath = Paths.get(fileName+"_" + System.currentTimeMillis());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath );
            }
            for (int i=0; i<10000; i++ ) {
                String content= "sys" + System.currentTimeMillis() + "\n";
                Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND);
                Thread.sleep(10000);
                currentPosition += content.length();
            }
        }
    }

    public static synchronized void writeToFile(String time) {
        try {

            if ( currentPosition + time.length() > memFileRollOverSize){
                fc.force(true);
                System.out.println( "Before truncate file " + fc.size() );

                fc.truncate(currentPosition);
                System.out.println( "After truncate file " + fc.size() );

                fc.close();

                rollOver();
            }
            ByteBuffer buffer = ByteBuffer.wrap(time.getBytes() );
            while (buffer.hasRemaining()) {
                getMappedBuffer().put(buffer);
            }
            System.out.println( "current position of file " + currentPosition );
            currentPosition += time.length();


        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    private static FileChannel getFileChannel() throws Exception {
        if ( fc == null ) {
            Path file = Paths.get(fileName+ "_" + System.currentTimeMillis());
            fc = FileChannel.open(file, READ, WRITE, CREATE);
        }
        return fc;
    }
    private static void rollOver() throws Exception {
            Path file = Paths.get(fileName+ "_" + System.currentTimeMillis());
            fc = FileChannel.open(file, READ, WRITE, CREATE);
        mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, memFileRollOverSize);
        currentPosition = 0l;

    }

    private static MappedByteBuffer getMappedBuffer() throws Exception {
        if ( fc == null ) {
            Path file = Paths.get(fileName+"_" + System.currentTimeMillis());
            fc = FileChannel.open(file, READ, WRITE, CREATE);
            mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, memFileRollOverSize);
        }
        return  mbb;
    }

    private static String getContent() throws Exception {
        return "content";
    }

}