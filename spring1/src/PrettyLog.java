import java.util.Date;

public class PrettyLog extends DefaultLog {

	public void write(String text)
	{
		Date today = new Date (); 
		System.out.println( "********************************************************************");
//		super.write(text);
		System.out.println( "* Date : "+ today +"   * Log : "  + text );
		System.out.println( "********************************************************************");
	}

}
