package tk.tommy.springboot;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.SshByPassword;
public class LinuxShellCommand2 {

	public static String server = "rd3-1";
	public static String line = "746386";

	public static void main(String argv[]) throws Exception {
		String cmd = "cat /mnt/nfs/var/" + server + "/mypaycenter.log | head -n " + line + " | tail -n 2000";
		Shell shell = new SshByPassword("192.168.0.21", 22, "root", "root@zonvan");
		String log = new Shell.Plain(shell).exec(cmd);
		System.out.println(log);
		FileUtils.writeStringToFile(
			new File("C:/Users/yu_da/OneDrive/Desktop/" + server + "-" + line + ".log"), log.toString(),
			"UTF-8");

	}
}