import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PipeRedirect extends Thread{
    private InputStream stdout;
    private OutputStream sstdin;
	
	public PipeRedirect(InputStream stdout, OutputStream sstdin) {
		this.stdout = stdout;
		this.sstdin = sstdin;
	}
	
	public void run() {
		try {
			int inputByte = stdout.read();
			
			while(inputByte != -1)
			{
				sstdin.write(inputByte);
				inputByte = stdout.read();
			}
			sstdin.close();
			
		}catch (Exception e) {
		  e.printStackTrace();
		}
	}

}

