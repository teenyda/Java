import java.io.*;
import java.net.*;
class  UserThread implements Runnable
{
private Socket s;
public UserThread(Socket s){
	this.s = s;
}
	public void run(){
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"...connected");
		try
		{
			for(int x = 0; x < 3; x++){
				BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String name = bufIn.readLine();
				BufferedReader bufr = new BufferedReader(new FileReader("d:/user.txt"));
				PrintWriter out = new PrintWriter(s.getOutputStream(),true);
				String line ;
				boolean flag = false;
				while ((line = bufr.readLine()) != null)
				{
					if (line.equals(name))
					{
						flag = true;
						break;
					}
				}
				if (flag)
				{	
					System.out.println(name+"�Ե�¼!");
					out.println(name+",��ӭ����!");
					break;
				}
				else {
					System.out.println(name+"���Ե�¼!");
					out.println(name+",�û��������ڣ�");
				}
			}
			s.close();
		}
		catch (Exception e)
		{
			throw new RuntimeException(ip+"У��ʧ��");
		}
	}	
}
