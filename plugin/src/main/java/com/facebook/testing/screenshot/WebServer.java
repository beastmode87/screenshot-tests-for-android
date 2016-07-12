// Copyright 2004-present Facebook. All Rights Reserved.

package com.facebook.testing.screenshot;

import org.eclipse.jetty.server.*;
import java.util.*;
import java.io.IOException;
import org.eclipse.jetty.server.handler.AbstractHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class WebServer {
  private int mPort;
  private Server mServer;

  public WebServer(int port) {
    mPort = port;
  }

  public void start() {
    mServer = new Server(mPort);

    Handler rootHandler = new MainHandler();
    ContextHandler rootContextHandler = new ContextHandler("/");
    rootContextHandler.setHandler(rootHandler);

    Handler[] handlers = new Handler[] {
      rootContextHandler
    };

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(handlers);
    mServer.setHandler(contexts);
    try {
      mServer.start();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void stop() {
    if (mServer != null) {
      try {
        mServer.stop();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

}