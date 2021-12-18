package com.rp.assignment;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Assignment04ReadFile {
    public static final Path path= Paths.get("src\\main\\resources\\assignment");
    public static final String rootFolder=System.getProperty("user.dir");
    public static final String resouceFolder="\\src\\main\\resources\\assignment";
    public static Flux<String> stringFlux(){
        return Flux.generate(()->{
                    FileReader fileReader=new FileReader(rootFolder+resouceFolder+"\\sec01");
                    Scanner sc=new Scanner(fileReader);
                    //String s = new String(Files.readAllBytes(path.resolve("")));
                    return sc;
                },
                (scanner, synchronousSink) -> {
                    if(scanner.hasNextLine()){
                        synchronousSink.next(scanner.nextLine());
                    }else{
                        synchronousSink.complete();
                    }
                    return scanner;
                },
                (s)-> {
                    System.out.println("Reading file is completed: "+s);
                    s.close();
                }
                );
    }

    public static void main(String[] args) {
        /*stringFlux().subscribe(
                (s)->{
                    System.out.println(s);
                },
                Util.onError(),
                Util.onComplete()

        );*/
        Flux.generate(callable(path),biFunction(),consumer());
    }
    public static Flux<String> readFileLineByLine(Path path){
        return Flux.generate(callable(path),biFunction(),consumer());
    }
    private static Callable<BufferedReader> callable(Path path){
        return ()->Files.newBufferedReader(path);
    }
    private static  BiFunction<BufferedReader, SynchronousSink<String>,BufferedReader> biFunction(){
        return (bufferedReader, synchronousSink)->{
            try {
                String str=bufferedReader.readLine();
                if(!Objects.isNull(str)){
                    synchronousSink.next(str);
                }else{
                    synchronousSink.complete();
                }

            } catch (IOException e) {
                synchronousSink.error(e);
                //e.printStackTrace();
            }
            return bufferedReader;
        };
    }
    private static Consumer<BufferedReader> consumer(){
        return (s)->{
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
