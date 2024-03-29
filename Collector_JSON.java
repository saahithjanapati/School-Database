import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.JSONParser;




public class Collector_JSON {


   public static void main(String args[]){
      
      
      Collector_JSON test = new Collector_JSON();
      
       
      ArrayList<Student> studentList = new ArrayList<Student>();
     
      test.userRequest(readFromFile(studentList)); 
   }


   
public Student getStudentInfo(){
   
       Student std = new Student();
       
      Scanner scanner1 = new Scanner(System.in);
      
      System.out.println("What is the ID of the student?");
      std.rollNumber = scanner1.nextLine();

      System.out.println("What is the first name of the student?");
      std.firstName = scanner1.nextLine();
      
      System.out.println("What is the last name of the student?");
      std.lastName = scanner1.nextLine();

    return std;

   }

   
public void printToFile(String info){
   try{
      FileWriter fileWriter = new FileWriter("data.txt");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      printWriter.println(info);
      printWriter.close();
      
      }
   catch(IOException e){
      System.out.println("There was an error with writing to the file");
   }
   }
   

public static ArrayList<Student> readFromFile(ArrayList<Student> list){
		
      try{
      
      BufferedReader br = new BufferedReader(new FileReader("students_info.json"));
      String jsonStudents = br.readLine();
      
      
      Gson Gson = new Gson();
      Type type = new TypeToken<ArrayList<Student>>() {}.getType();
      list = Gson.fromJson(jsonStudents, type);
      return list;


      
      
      }
      catch(Exception e){
   e.printStackTrace();
   }
         
     return list;
      
      }
      
      
public void viewInfo(ArrayList<Student> listname){
   System.out.println("Do you want to print the information of every student?");
         Scanner scanner1 = new Scanner(System.in);
         String response = scanner1.nextLine();
         
         
            if ((response.equals("yes")) || (response.equals("Yes"))){
               
               Iterator<Student> iterator = listname.iterator();
               
               String printoutToFile = "";
               
               while(iterator.hasNext()){
                                  
                  Student next = iterator.next();
                  String printout; 
                  printout = next.getRollNumber() + " "+  next.getFullName();
                  System.out.println(printout);
                  printout = next.getRollNumber() + " "+  next.getFullName() + "\n";
                  
                  printoutToFile += printout;
                  
                  
               }
               
               printToFile(printoutToFile);
               
               
            }
            
            else if ((response.equals("no")) || (response.equals("No"))){
               
               
               Boolean loopContinuestwo = true;
               ArrayList<String> NamesList = new ArrayList<String>();
               while (loopContinuestwo){
                  System.out.println("Enter the id number of the student you want to print. You can add more later");
                  NamesList.add(scanner1.nextLine());
                  System.out.println("Do you want to specify more students?");
                     String response3 = scanner1.nextLine();
                     if ((response3.equals("no")) || (response3.equals("No"))){
                        loopContinuestwo = false;
                        
                        
                        
                        for(String nexty : NamesList){
                        
                           for(Student next : listname){
                           
                              if ((nexty).equals(next.getRollNumber())){

                           System.out.println(next.getRollNumber() + " "+  next.getFullName());}
               

                        
                     }

               }

                        }      
            }
      
     }

}


public void addInfo(ArrayList<Student> listname){
 Boolean loopContinuesone;
      
      Scanner scanner2 = new Scanner(System.in);
      String answerToLoopContinues;
      Boolean noOutput = true;
      Boolean loopContinuesseven = true;
      
      
          
      while(loopContinuesseven){
         
         System.out.println("Do you have student information to enter? Please enter yes or no");
            answerToLoopContinues = scanner2.nextLine();
         
         if (answerToLoopContinues.equals("yes")){
            
            
            Student temp;
            temp = getStudentInfo();
            listname.add(temp);
            noOutput = false;
            }
         
         else if (answerToLoopContinues.equals("no")){
            loopContinuesseven = false;
         }   

         
         }

}


public void deleteInfo(ArrayList<Student> listname){
   Scanner scanner3 = new Scanner(System.in);

   try{
     Boolean loopContinueseight = true;
     while (loopContinueseight){
      System.out.println("Do you have student information that you want to delete? Please enter yes or no");
      String response12 = scanner3.nextLine();
      
      if (response12.equals("yes")){
         System.out.println("Enter the id of the student you want to delete");
         
         String idnumber = scanner3.nextLine();
         for(Student next : listname){
            if (next.rollNumber.equals(idnumber)){
               listname.remove(listname.indexOf(next));
            }
         }
         
   
      }
      
      else if (response12.equals("no")){
      loopContinueseight = false;
      }
      
     
     }
     }
     
     catch (java.util.ConcurrentModificationException e){
     
     }

   }


public void saveInfo(ArrayList<Student> listname){
   
   Gson gson = new Gson();
   String jsonstudents = gson.toJson(listname);
   System.out.println(jsonstudents);

   try {
			FileWriter fileWriter = new FileWriter("students_info.json");
			fileWriter.write(gson.toJson(listname));
			fileWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
     

}



public void userRequest(ArrayList<Student> listname){
   Boolean userRequestContinues = true;
   
   while(userRequestContinues){
   System.out.println("******************************************** \n What do you want to do? \n 1 view info \n 2 add info \n 3 delete info \n S save changes \n E exit");
   Scanner scanner2 = new Scanner(System.in);
   String userPurpose = scanner2.nextLine();
   
   if (userPurpose.equals("1")){
      viewInfo(listname);  
      }
            
            
            
     if (userPurpose.equals("2")){
      addInfo(listname);
     }
     
     
     
     if(userPurpose.equals("3")){
      deleteInfo(listname);}
          
     
     
     if(userPurpose.equals("S")){
      saveInfo(listname);
     } 
     
     
   
   if(userPurpose.equals("E")){
      userRequestContinues = false;

   }   
   }
}}