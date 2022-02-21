package idea.verlif.socket.command.cmd;

import idea.verlif.socket.command.SocketCommand;
import idea.verlif.socket.core.server.holder.ClientHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/2/11 14:24
 */
public class CmdCommand implements SocketCommand<CmdConfig> {

    private CmdConfig cmdConfig;

    @Override
    public String[] keys() {
        return new String[]{"cmd"};
    }

    @Override
    public void onLoad(CmdConfig cmdConfig) {
        this.cmdConfig = cmdConfig;
    }

    @Override
    public void run(ClientHolder.ClientHandler clientHandler, String s) {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            Process p = pb.directory(cmdConfig.getWorkFile()).command("cmd.exe", "/c", s).start();
            InputStream inStream = p.getInputStream();
            InputStreamReader inReader = new InputStreamReader(inStream, Charset.forName("GBK"));
            BufferedReader inBuffer = new BufferedReader(inReader);
            String out;
            while ((out = inBuffer.readLine()) != null) {
                clientHandler.sendMessage(out);
            }
            inStream.close();
            p.destroy();
        } catch (IOException e) {
            clientHandler.sendMessage(e.getMessage());
        }
    }
}
