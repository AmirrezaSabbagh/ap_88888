import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
public class Managment {
    private static void add (Note note){
        ArrayList<Note> notes = Managment.readFromFile();
        notes.add(note);
        Managment.writeToFile(notes);
    }
    private static void ShowTheText(int index){
        ArrayList<Note> notes = Managment.readFromFile();
        for(String a : notes.get(index).getBody()){
            System.out.println(a);
        }
    }
    private static ArrayList<String> makeNote(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        try{
            while (!(line = reader.readLine()).equals("#")) {
                lines.add(line);
            }
            return lines;
        }
        catch (Exception e){
            return null;
        }
    }
    private static ArrayList<Note> readFromFile(){
        ArrayList<Note> notes = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("client.ser")))){
            try{
                while(true){
                    Note note = (Note) input.readObject();
                    notes.add(note);
                }
            }
            catch (Exception ee){}
        }
        catch (Exception e){}
        return notes;
    }
    private static void writeToFile(ArrayList<Note> notes){
        try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("client.ser")))){
            for(Note a : notes){
                output.writeObject(a);
            }
        }
        catch (Exception e){}
    }
    private static int Show() {
        int i = 1;
        try(ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("client.ser")))){
            try {
                while (true) {
                    Note note = (Note) input.readObject();
                    System.out.print(i++ +"- ");
                    System.out.println(note.getTitle()+"  "+note.getDate());
                }
            } catch (Exception ee) {}
        } catch (Exception e) {}
        return i;
    }
    public static int menu(){
        while(true){
            System.out.println("1-add");
            System.out.println("2-remove");
            System.out.println("3-notes");
            System.out.println("4-export");
            Scanner input = new Scanner(System.in);
            int result = input.nextInt();
            input.nextLine();
            if(result == 1){
                System.out.println("please choose a title: ");
                String title = input.nextLine();
                ArrayList<Note> notes = Managment.readFromFile();
                if(notes.stream().map(s->s.getTitle()).collect(Collectors.toList()).contains(title)){
                    System.out.println("this title exist");
                }
                else{
                    System.out.println("so now you can write anything you want , just enter # to finish");
                    Note note = new Note(title , makeNote());
                    add(note);
                }
            }
            else if(result == 2){

            }
            else if(result == 3){
                if(Show()!=1){
                    int index = input.nextInt();
                    ShowTheText(index-1);
                }
                else{
                    System.out.println("there isn't any text here");
                }
            }
            else if(result == 4){

            }
        }
    }
}