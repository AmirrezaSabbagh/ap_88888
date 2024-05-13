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
    private static void remove(int x){
        ArrayList<Note> arrayList=Managment.readFromFile();
        try {
            exception(x,arrayList.size());
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.getMessage();
        }
        arrayList.remove(x);
        Managment.writeToFile(arrayList);

    }
    private static void Export(int x) {
        ArrayList<Note> arrayList = Managment.readFromFile();
        String fileName = arrayList.get(x).getTitle();
        FileWriter writer = null;
        try {
            exception(x, arrayList.size());
            fileName = arrayList.get(x).getTitle();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index selected.");
            return;
        }
        try {
            writer = new FileWriter(fileName); // Create a new FileWriter object
            writer.write(String.join("\n", arrayList.get(x).getBody())); // Write the text to the file
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close(); // Close the writer
                } catch (IOException e) {
                    System.err.println("Error closing the FileWriter.");
                    e.printStackTrace();
                }
            }
        }
    }
    public static void exception(int x ,int size)throws ArrayIndexOutOfBoundsException{
        if (x + 1 >size) {
            throw new IndexOutOfBoundsException("you can choose a number beetween 1 to"+ size);
        }
    }
    public static int menu(){
        while(true){
            System.out.println("1-add");
            System.out.println("2-remove");
            System.out.println("3-notes");
            System.out.println("4-export");
            System.out.println("5-exit");
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
                System.out.println("choose one of the notes to remove or enter 0 to back to menu");
                Show();
                int index = input.nextInt();
                remove(index -1);
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
                System.out.println("choose a note to export");
                Show();
                int index = input.nextInt();
                Export(index -1);
            }
            else if(result == 5){
                break;
            }
        }
    }
}