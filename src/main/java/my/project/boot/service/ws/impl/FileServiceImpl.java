package my.project.boot.service.ws.impl;

import com.laxcus.log.client.Logger;
import com.laxcus.tub.servlet.TubException;
import com.laxcus.tub.servlet.TubServlet;
import com.laxcus.tub.servlet.TubStartResult;
import com.laxcus.tub.servlet.TubStopResult;
import my.project.boot.service.ws.FileService;
import org.springframework.stereotype.Service;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

@Service
public class FileServiceImpl extends TubServlet implements FileService {
    private Thread thread;

    private volatile boolean running;

    private boolean interrupted;

    private DatagramSocket socket;

    FileServiceImpl() {
        super();
        thread = null;
        running = false;
        interrupted = false;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public TubStartResult launch(String s) throws TubException {
        return null;
    }

    @Override
    public TubStopResult stop(String s) throws TubException {
        return null;
    }

    @Override
    public void login() {

    }

    @Override
    public void store() {

    }

    @Override
    public void extract() {

    }

    private boolean bind(SocketAddress address) {
        boolean success = false;
        try {
            socket = new DatagramSocket(null);
            socket.bind(address);
            socket.setReceiveBufferSize(10240);
            socket.setSendBufferSize(10240);
            socket.setSoTimeout(0); // no limit time
            success = true;
        } catch (SocketException e) {
            Logger.error(e);
        } catch (Throwable e) {
            Logger.fatal(e);
        }
        return success;
    }
}
