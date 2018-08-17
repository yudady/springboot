package tk.tommy.springboot;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

import org.apache.commons.io.FileUtils;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.SshByPassword;
public class LinuxShellCommand {

	String[] fileNames = new String[]{"mypaycenter", "redirect", "callback", "orderError"};
	String[] serverNames = new String[]{"rd3", "rd3-1", "rd3-2"};

	private boolean isOnLinePay(String orderNo) {
		return orderNo.startsWith("M");
	}

	private String getOrderNoDate(String orderNo) {
		String orderDate = null;
		if (isOnLinePay(orderNo)) {
			orderDate = orderNo.substring(1, 9);
		} else {
			orderDate = orderNo.substring(0, 8);
		}
		return orderDate;
	}

	private String getOrderNoDateFormat(String orderNo) {
		String orderDate = getOrderNoDate(orderNo);
		return orderDate.substring(0, 4) + "-" + orderDate.substring(4, 6) + "-" + orderDate.substring(6, 8);
	}

	private boolean isToday(String orderNo) {
		String tDate = LocalDate.now().toString().replace("-", "");
		return getOrderNoDate(orderNo).equalsIgnoreCase(tDate);
	}

	private Map<String, List<String>> findCommands(String orderNo, boolean addLineNumber) {

		Map<String, List<String>> returnVal = new LinkedHashMap<>();

		for (String serverName : serverNames) {
			List<String> cmds = new ArrayList<>();
			returnVal.put(serverName, cmds);

			for (String fileName : fileNames) {
				StringBuffer cmd = new StringBuffer();

				if (isToday(orderNo)) {
					cmd.append("grep ");
				} else {
					cmd.append("zgrep ");
				}

				if (addLineNumber) {
					cmd.append(" -n ");
				}
				cmd.append(orderNo);

				if (isToday(orderNo)) {
					cmd.append(" /mnt/nfs/var/" + serverName + "/" + fileName + ".log");
				} else {
					cmd.append(" /mnt/nfs/var/" + serverName + "/LOG_BACKUP/" + fileName + "-"
						+ getOrderNoDateFormat(orderNo) + ".log.zip");
				}

				cmds.add(cmd.toString());
			}

		}

		return returnVal;
	}

	private Map<String, List<String>> findCommands(String orderNo) {
		return findCommands(orderNo, false);
	}

	private String findLogByCommand(String serverName, List<String> cmds) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(serverName + " :").append("\n");
		stringBuffer.append(serverName + " :").append("\n");
		stringBuffer.append(serverName + " :").append("\n");
		Shell shell = new SshByPassword("192.168.0.21", 22, "root", "root@zonvan");
		for (int i = 0; i < cmds.size(); i++) {
			String cmd = cmds.get(i);
			String fileName = fileNames[i];
			stringBuffer.append(serverName + "---" + fileName + " :").append("\n");

			String log = new Shell.Plain(shell).exec(cmd);
			stringBuffer.append(log);
		}
		stringBuffer.append("\n").append("\n").append("\n");

		return stringBuffer.toString();
	}

	public static void main(String argv[]) throws Exception {

		String orderNo = "M201808170010001523";
		LinuxShellCommand c = new LinuxShellCommand();
		Map<String, List<String>> cms = c.findCommands(orderNo, true);

		Iterator<Map.Entry<String, List<String>>> iterator = cms.entrySet().iterator();
		StringBuffer msgs = new StringBuffer();
		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> pair = iterator.next();
			String key = pair.getKey();
			List<String> cmds = pair.getValue();
			msgs.append(c.findLogByCommand(key, cmds));
		}

		FileUtils.writeStringToFile(new File("C:/Users/yu_da/OneDrive/Desktop/" + orderNo + ".log"),
			msgs.toString(), "UTF-8");

	}
}