import java.math.BigInteger;
import java.sql.*;
import java.util.*;
public class TASK_5 {
    Scanner s=new Scanner(System.in);
    public void add(Statement st) throws SQLException{
        System.out.print("\tEnter roll_no: ");
        int r=s.nextInt();
        System.out.print("\tEnter name of the student: ");
        String name=s.next();
        System.out.print("\tEnter phone number: ");
        BigInteger ph=s.nextBigInteger();
        System.out.print("\tEnter location: ");
        String loc=s.next();
        String q="INSERT INTO student(roll_no,name,phone,location) VALUES("+r+",\""+name+"\","+ph+",\""+loc+"\")";
        st.executeUpdate(q);
        System.out.println("\tStudent record added successfully\n");
    }
    public void edit(Statement st) throws SQLException{
        System.out.print("\tEnter roll number: ");
        int r=s.nextInt();
        System.out.print("\tEnter 1 to update name,2 to update phone_no,3 to update location: ");
        int choice=s.nextInt();
        if(choice==1){
            System.out.print("\tEnter New name: ");
            String name=s.next();
            String q="UPDATE student SET name=\""+name+"\" "+"WHERE"+" roll_no="+r;
            st.executeUpdate(q);
            System.out.println("\tStudent name updated successfully\n");
        }
        else if(choice==2){
            System.out.print("\tEnter New phone_no: "); 
            int no=s.nextInt();
            String q="UPDATE student SET phone="+no+" "+"WHERE"+" roll_no="+r;
            st.executeUpdate(q);
            System.out.println("\tStudent phone_no updated successfully\n");
        }
        else if(choice==3){
            System.out.print("\tEnter New location: "); 
            String loc=s.next();
            String q="UPDATE student SET location=\""+loc+"\" "+"WHERE"+" roll_no="+r;
            st.executeUpdate(q);
            System.out.println("\tStudent location updated successfully\n");
        }
        else{
            System.out.println("\tPlease enter correct choice for updation");
        }
    }
    public void remove(Statement st) throws SQLException{
        System.out.print("\tEnter roll number to delete: ");
        int r=s.nextInt();
        String q="DELETE FROM student WHERE roll_no="+r;
        st.executeUpdate(q);
        System.out.println("\tStudent record deleted successfully");
    }
    public void display(Statement st) throws SQLException{
        ResultSet rs=st.executeQuery("SELECT * FROM student");
        System.out.println("\n\tTotal students information:\n");
        System.out.println("\troll_no\t name\tphone\t\tlocation\n");
        while(rs.next()){
            System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getBigDecimal("phone")+"\t"+rs.getString(4)+"\n");
        }
        System.out.println();
    }
    public void fetch_info(Statement st) throws SQLException{
        System.out.print("\tEnter roll number to fetch information: ");
        int r=s.nextInt();
        ResultSet rs=st.executeQuery("SELECT * from student WHERE roll_no="+r);
        System.out.println("\troll_no\tname\tphone\t\tlocation\n");
        System.out.println();
        while(rs.next()){
            System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getBigDecimal("phone")+"\t"+rs.getString(4)+"\n");
        }
        System.out.println();
    }
    public static void main(String[] args) {
    App a=new App();
    Scanner sc=new Scanner(System.in);
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CODSOFT","root","mysql");
        Statement st= connection.createStatement();
        System.out.println();
        System.out.println("\t\t\t\t\t\tSTUDENT MANAGEMENT SYSTEM");
        System.out.println("\t\t\t\t\t\t_________________________\n");
        System.out.println("\t** STUDENT ATTRIBUTES: roll_no, name, phone, location **\n");
        System.out.println("\tChoose operation:\n\t________________\n");
        System.out.println("\t1.Add student\n\t2.Fetch student information by roll_no\n\t3.Remove a student\n\t4.Display all the students\n\t5.Edit student information\n\t6.Exit\n");
        while(true){
            System.out.print("\tEnter your choice:");
            int choice=sc.nextInt();
            switch(choice){
                case 1: a.add(st);
                        break;
                case 2: a.fetch_info(st);
                        break;
                case 3: a.remove(st);
                        break;
                case 4: a.display(st);
                        break;
                case 5: a.edit(st);
                        break;
                case 6: sc.close();
                        System.exit(0);
                default:System.out.println("\tPlease enter correct choice");
                        continue;

            }
        }

    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
}
}