package main.java.com.thread;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileWrite {

    public static void main(String[] args) {

    }
    ExecutorService service = Executors.newFixedThreadPool(4);
    Map<String, File> map  = new HashMap<>();
}
