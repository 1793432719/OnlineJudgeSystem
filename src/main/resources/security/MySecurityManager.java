import java.security.Permission;

/**
 * @author jillion
 * @create com.niupi.niuojcodesandbox.security - the name of the target package where the new class or interface will
 * be created. niuoj-code-sandbox - the name of the current project. null.java - the name of the PHP file that will be created. MySecurityManager - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/8 - the current system date. 16:55 - the current system time. 2024 - the current year. 01 - the current month. 08 - the current day of the month. 16 - the current hour. 55 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
public class MySecurityManager extends SecurityManager {
    //检查所有的权限的方法
    @Override
    public void checkPermission(Permission perm) {
//        super.checkPermission(perm);
    }

    /**
     * 检测程序是否可执行文件
     *
     * @param cmd the specified system command.
     */
    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("checkExec权限异常" + cmd);
    }

    /**
     * 检测程序是否允许读文件
     *
     * @param file the system-dependent file name.
     */
    @Override
    public void checkRead(String file) {
//        throw new SecurityException("checkRead权限异常" + file);

    }

    /**
     * 检测程序是否允许写文件
     *
     * @param file the system-dependent filename.
     */
    @Override
    public void checkWrite(String file) {
//        throw new SecurityException("checkWrite权限异常" + file);
    }

    /**
     * 检测程序是否允许删除文件
     *
     * @param file the system-dependent filename.
     */
    @Override
    public void checkDelete(String file) {
//        throw new SecurityException("checkDelete权限异常" + file);
    }

    /**
     * 检测程序是否允许链接网络
     *
     * @param host the host name port to connect to.
     * @param port the protocol port to connect to.
     */
    @Override
    public void checkConnect(String host, int port) {
//        throw new SecurityException("checkConnect权限异常" + host + ":" + port);
    }
}
