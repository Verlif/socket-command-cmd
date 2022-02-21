package idea.verlif.socket.command.cmd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import idea.verlif.socket.command.config.ConfigAdapter;

import java.io.File;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/2/21 16:44
 */
public class CmdConfig implements ConfigAdapter {

    /**
     * 指令工作目录
     */
    private File workDir = null;

    @Override
    public String key() {
        return "cmd";
    }

    public String getWorkDir() {
        return workDir == null ? "." : workDir.getAbsolutePath();
    }

    public void setWorkDir(String baseDir) {
        if (baseDir != null && baseDir.length() > 0) {
            this.workDir = new File(baseDir);
        }
    }

    @JsonIgnore
    public File getWorkFile() {
        if (workDir == null) {
            workDir = new File(".");
        }
        return workDir;
    }
}
