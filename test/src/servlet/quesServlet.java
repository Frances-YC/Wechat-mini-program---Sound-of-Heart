package servlet;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;



public class quesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//�����������
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* ������Ӧͷ����ajax������� */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        try{
        	int m1=0;
        	int m2=0;
        	int m3=0;
        	int m4=0;
        	int m5=0;
        	int m6=0;
        	int m7=0;
        	int m8=0;
        	int m9=0;
            String topicid=request.getParameter("topicid");
            String q1=request.getParameter("q1");
            String q2=request.getParameter("q2");
            String q3=request.getParameter("q3");
            String q4=request.getParameter("q4");
            String q5=request.getParameter("q5");
            String q6=request.getParameter("q6");
            String q7=request.getParameter("q7");
            String q8=request.getParameter("q8");
            String q9=request.getParameter("q9");
            String q10=request.getParameter("q10");
            String[] str={q1,q2,q3,q4,q5,q6,q7,q8,q9,q10};
            int index=0;
            System.out.println(topicid);
            System.out.println(q1);
            System.out.println(q2);
            System.out.println(q3);
            System.out.println(q4);
            System.out.println(q5);
            System.out.println(q6);
            System.out.println(q7);
            System.out.println(q8);
            System.out.println(q9);  
            System.out.println(q10);
        	System.out.println("���ܳɹ���");
        	//���в�ͬ���˸�ķ�ֵռ�ȵ�ƥ��
        	Class.forName("com.mysql.jdbc.Driver"); // ����MYSQL						
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tsh?user=root&password=pop966100");
        	Statement stmt1 = connect.createStatement();      	 
        	String ssql="select aid,tid,M1,M2,M3,M4,M5,M6,M7,M8,M9 from question_answer where tid="+"'"+topicid+"'"; 
        	
        	ResultSet rs = stmt1.executeQuery(ssql);
        	rs.last();
        	rs.beforeFirst();
        	    while(rs.next())     //�ҳ�ÿһ����ǩ�µ�����ı�ţ���ʹ������ȥƥ��
        	    {   
        	    	String aid=rs.getString(1);
        	        int  M1=rs.getInt(3);
        	        int  M2=rs.getInt(4);
        	        int  M3=rs.getInt(5);
        	        int  M4=rs.getInt(6);
        	        int  M5=rs.getInt(7);
        	        int  M6=rs.getInt(8);
        	        int  M7=rs.getInt(9);
        	        int  M8=rs.getInt(10);
        	        int  M9=rs.getInt(11);
        	        if(str[index].equals(aid))
        	         {   
	        	        m1=m1+M1;
	        	        m2=m2+M2;
	        	        m3=m3+M3;
	        	        m4=m4+M4;
	        	        m5=m5+M5;
	        	        m6=m6+M6;
	        	        m7=m7+M7;
	        	        m8=m8+M8;
	        	        m9=m9+M9;
	        	        index++;
        	        }  
        	        if(index==9)
        	        {
        	        	break;
        	        }
        	      }       	   
        	  rs.close();  
              System.out.println(m1);
              System.out.println(m2);
              System.out.println(m3);
              System.out.println(m4);
              System.out.println(m5);
              System.out.println(m6);
              System.out.println(m7);
              System.out.println(m8);
              System.out.println(m9);
        	  //�ٴδ����������˸�ķ�ֵ�ĸ���,ʹ��whileѭ���������ݽ��и���
              int n=0;
              int i=1;
              int[] m={m1,m2,m3,m4,m5,m6,m7,m8,m9};  //�洢�ύ��Ĵ𰸵ķ�����ֵ
              while(n<9){
              String str1= "update u_mood set mark=mark+? where mid="+"'"+"m0"+i+"'";
              PreparedStatement pstmt1 = connect.prepareStatement(str1);
              pstmt1.setInt(1, m[n]);
        	  int x= pstmt1.executeUpdate();
        	  if(x>0)  //����һ�θ�����ɣ��򽫱��εĲ�ѯ�رգ�����������������һ�εĸ��¡�����û����ɣ���ᱨ������ֹͣ
        	   {
        	    pstmt1.close();
        	   }
        	  n++;
        	  i++;
              }              
              //����ֵ��΢��С����
            Writer out = response.getWriter();
            out.write("�����Ѿ����µ����ݿ��У�");
            out.flush();
        		       }
        catch (Exception e) 
        {
        	System.out.println(e);  
            e.printStackTrace();
       	}
        //result.put("data", user);
        //result.put("msg", "��̨���յ�");
        //ʹ��Gson����Ҫ����gson-2.8.0.jar
        //String json = new Gson().toJson(result);

        //����ֵ��΢��С����
        
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	public void init() throws ServletException {
	}
}
