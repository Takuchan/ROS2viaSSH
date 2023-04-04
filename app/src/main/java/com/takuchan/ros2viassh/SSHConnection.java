package com.takuchan.ros2viassh;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.*;
public class SSHConnection extends AsyncTask<Void,Void,Void> {
    private String user;
    private String password;
    private String host;
    private JSch jsch;
    private Session session;
    private Channel channel;
    public SSHConnection(String user, String password, String host) {
        this.user = user;
        this.password = password;
        this.host = host;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            jsch = new JSch();
            session = jsch.getSession(user, host);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // SSH接続が成功した場合の処理
            channel = session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();

        } catch (JSchException e) {
            // SSH接続が失敗した場合の処理
            e.printStackTrace();
        }


        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        // 非同期処理が完了した後に実行する処理
        if (session != null) {
            session.disconnect();
        }
        if (channel != null) {
            channel.disconnect();
        }
    }
}
