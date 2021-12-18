package com.rp.assignment;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Paths;

import static com.rp.assignment.FileService.resouceFolder;
import static com.rp.assignment.FileService.rootFolder;

public class MainClass {
    public static void main(String[] args) {
        //FileService fileService=new FileService();
        //Mono<ReadFileService> readFileServiceMono=Mono.fromSupplier(()->  (s)->new File(rootFolder+resouceFolder+"\\"+s));

        //read("sec01").subscribe((s)-> System.out.println(s));
        write("sec02","abcdmnop\nhh").subscribe(
                (s)-> System.out.println("n"),
                (throwable)-> System.out.println(throwable),
                ()-> System.out.println("completed----")
        );
        read("sec02").subscribe((s)-> System.out.println(s));
        delete("sec02").subscribe(
                (s)-> System.out.println("n"),
                (throwable)-> System.out.println(throwable),
                ()-> System.out.println("deleted...")
        );
        System.out.println(">>>>>>>>>>>");
        Assignment04ReadFile.readFileLineByLine(Paths.get("src\\main\\resources\\assignment\\sec01"))
                .take(2)
                .subscribe(Util.subscriber());
    }
    public static Mono<String> read (String fileName){
        return Mono.fromSupplier(
                ()->FileService.readFile2(fileName)
        );
    }
    public static Mono<Void> write (String fileName,String content){
        return Mono.fromRunnable(
                ()->FileService.writeFile2(fileName,content)
        );
    }
    public static Mono<Void> delete (String fileName){
        return Mono.fromRunnable(
                ()->FileService.deleteFile2(fileName)
        );
    }

}
