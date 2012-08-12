package be.groept.support;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hsqldb.Server;
import org.hsqldb.ServerConfiguration;
import org.hsqldb.ServerConstants;
import org.hsqldb.persist.HsqlProperties;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Bean that will start an instance of an HSQLDB database. In contrast to the in
 * memory variant you can connect via a normal JDBC client to this database.
 * <p>
 * A possible configuration might look like this;
 * 
 * <pre>
 * 	&lt;bean id="database" class="be.vlaanderen.fb.vfp.hsqldb.HsqldbServerBean" lazy-init="false"&gt;
 * 	&lt;property name="serverProperties"&gt;
 * 		&lt;props&gt;
 * 			&lt;prop key="server.port"&gt;9000&lt;/prop&gt;
 * 			&lt;prop key="server.database.0"&gt;mem:test&lt;/prop&gt;
 * 			&lt;prop key="server.dbname.0"&gt;test&lgt;/prop&gt;
 * 		&lt;/props&gt;
 * 	&lt;/property&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * The connection URL would then be: "jdbc:hsqldb:hsql://localhost:9000/test".
 * 
 * @see org.hsqldb.Server
 * 
 * @author Koen Serneels
 */
public class HsqldbServerBean implements InitializingBean, DisposableBean {

	private final Logger logger = Logger.getLogger(HsqldbServerBean.class);

	/**
	 * Properties used to customize instance.
	 */
	private Properties serverProperties;

	/**
	 * The actual server instance.
	 */
	private Server server;

	/**
	 * DataSource used for shutdown.
	 */
	private DataSource dataSource;

	public void setServerProperties(Properties serverProperties) {
		this.serverProperties = serverProperties;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void afterPropertiesSet() throws Exception {

		HsqlProperties configProps = new HsqlProperties(serverProperties);
		configProps.setProperty(ServerConfiguration.SC_DEFAULT_ADDRESS, "jdbc:hsqldb:.:test");
		configProps.setProperty(ServerConfiguration.SC_DEFAULT_DATABASE, "jdbc:hsqldb:.:test");

		ServerConfiguration.translateDefaultDatabaseProperty(configProps);

		server = new Server();
		server.setRestartOnShutdown(false);

		server.setNoSystemExit(true);
		server.setProperties(configProps);

		logger.info("HSQL Server startup sequence initiated");
		if (portAvailable()) {
			server.start();
			logger.info("HSQLDB Server listening on port: " + server.getPort());
		} else {
			logger.warn("HSQLDB Server was NOT started, the port " + server.getPort() + " was already in use");
		}
	}

	private boolean portAvailable() {
		Socket socket = null;
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), server.getPort()), 1000);
		} catch (UnknownHostException e) {
			return true;
		} catch (IOException e) {
			return true;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// Do nothing
				}
			}
		}
		return false;
	}

	public void destroy() {
		logger.info("HSQLDB Server shutdown sequence initiated");
		if (dataSource != null) {
			Connection con = null;
			try {
				con = dataSource.getConnection();
				con.createStatement().execute("SHUTDOWN");
			} catch (SQLException e) {
				logger.error("HSQLDB Server shutdown failed: " + e.getMessage());
			} finally {
				try {
					if (con != null) {
						con.close();
					}
				} catch (Exception ignore) {
					// ignore
				}
			}
		} else {
			logger.warn("HsqldbServerBean needs a dataSource property set to shutdown the database safely.");
		}
		server.signalCloseAllServerConnections();
		int status = server.stop();
		long timeout = System.currentTimeMillis() + 5000;
		while (status != ServerConstants.SERVER_STATE_SHUTDOWN && System.currentTimeMillis() < timeout) {
			try {
				Thread.sleep(100);
				status = server.getState();
			} catch (InterruptedException e) {
				logger.error("Error while shutting down HSQLDB Server: " + e.getMessage());
				break;
			}
		}
		if (status != ServerConstants.SERVER_STATE_SHUTDOWN) {
			logger.warn("HSQLDB Server failed to shutdown properly.");
		} else {
			logger.info("HSQLDB Server shutdown completed");
		}
		server = null;
	}

}