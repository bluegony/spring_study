

public class Main {
	public static void main(String args[])
	{
		Client a = new Client();
		a.showLog();

		PrettyLog p = new PrettyLog();
		a.setLogHandler(p);
		a.showLog();
		
	}

}
