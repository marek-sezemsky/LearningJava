package person.sezemsky.marek.learning.java;

import com.github.tomaslanger.chalk.Chalk;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fusesource.jansi.AnsiConsole;

public class LearningJava {

    private static final Logger LOG = LogManager.getLogger(LearningJava.class);

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final long DEFAULT_SEED = 42;

    private static RandomPersonGenerator rpg;

    public static void main(String[] args) throws UnsupportedEncodingException {
        LOG.debug("Initiating startup sequence ..");
        System.out.println(Chalk.on(
                "\nBOOTUP Loading version 701.12 UPG").red().bold());
        
        try {
            String encoding = System.getProperty("file.encoding", DEFAULT_ENCODING);
            boolean autoFlush = true;
            LOG.info("Using output encoding " + encoding);
            System.setOut(new PrintStream(System.out, autoFlush, encoding));
        } catch (RuntimeException e) {
            LOG.error("FUCK UP: ", e);  // lolwut
        }

        System.out.println(Chalk.on(
                "\nSYSCHK ...").blue().bold());

        Runtime rt = Runtime.getRuntime();
        long cpus = rt.availableProcessors();
        long free = rt.freeMemory() >> 20;  // bytes to mega
        long max = rt.maxMemory() >> 20;
        long total = rt.totalMemory() >> 20;
        System.out.println(String.format(
                "Runtime: %d CPUs / %,d total %,d max %,d free MEM [MB]",
                cpus, total, max, free));

        System.out.println(Chalk.on(
                "SYSCHK: DONE").green().bold());
        
        System.out.println(Chalk.on(
                "\nSYSTEM READY\n").bgGreen().white().bold());
        LOG.debug("System ready");

        rpg = new RandomPersonGenerator(new Random(DEFAULT_SEED));
        LOG.debug("Generator: " + rpg);

        LOG.info("Generating 10 random persons ...");
        for (int i = 0; i < 10; i++) {
            Person p = rpg.getRandomPerson();
            LOG.debug("Got " + p);
            System.out.println(p);
        }

    }
    
}
