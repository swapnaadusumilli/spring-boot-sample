package bootpoc

import org.apache.commons.daemon.Daemon
import org.apache.commons.daemon.DaemonContext
import org.apache.commons.daemon.DaemonInitException
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * User: victor
 * Date: 6/18/14
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
class Main implements Daemon {

    private static ConfigurableApplicationContext ctx

    static void main(String[] args) {
        ctx = SpringApplication.run(Main.class, args);
    }

    @Override
    void init(DaemonContext daemonContext) throws DaemonInitException, Exception {

    }

    @Override
    void start() throws Exception {
        main()
    }

    @Override
    void stop() throws Exception {
        ctx.close()
    }

    @Override
    void destroy() {

    }
}
