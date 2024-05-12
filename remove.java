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


