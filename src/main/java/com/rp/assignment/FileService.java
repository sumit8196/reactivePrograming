package com.rp.assignment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileService {
    public static final String rootFolder=System.getProperty("user.dir");
    public static final String resouceFolder="\\src\\main\\resources\\assignment";
    public static final Path path= Paths.get("src\\main\\resources\\assignment");

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(readFile("sec01"));
        //createAndWrite("sec01");
        //deleteFile("sec04");
    }
    public static String readFile(String fileName){
        StringBuilder output= new StringBuilder();
        try {
            File fileReader=new File(rootFolder+resouceFolder+"\\"+fileName+"");
            Scanner sc=new Scanner(fileReader);
            while (sc.hasNextLine()){
                //System.out.println(sc.nextLine());
                output.append(sc.nextLine());
                output.append("\n");
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    public static String readFile2(String filename){
        String out=null;
        try {
            out= new String(Files.readAllBytes(path.resolve(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
    public static void  writeFile2(String filename,String content){
        try {
            Files.write(path.resolve(filename),content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void  deleteFile2(String filename){
        try {
            Files.delete(path.resolve(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFile(File file){
        StringBuilder output= new StringBuilder();
        try {
            File fileReader=new File(rootFolder+resouceFolder+"\\"+file.getName()+"");
            Scanner sc=new Scanner(fileReader);
            while (sc.hasNextLine()){
                //System.out.println(sc.nextLine());
                output.append(sc.nextLine());
                output.append("\n");
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(output);
        return output.toString();
    }
    public static String createAndWrite(String fileName){
        File file=new File(rootFolder+resouceFolder+"\\"+fileName);
        try {
            file.createNewFile();
            System.out.println("File is created %s"+file.getName());
            FileWriter fileWriter=new FileWriter(file);
            for(int i=0;i<5;i++){
                fileWriter.write("Line no "+i+" random texts END:::\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file.getName();
    }
    public static void deleteFile(String fileName){
        File file=new File(rootFolder+resouceFolder+"\\"+fileName);
        file.delete();
        System.out.println("file is deleted successfully :"+fileName);
    }
}
