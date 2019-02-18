package person.sezemsky.marek.learning.java;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fusesource.jansi.AnsiConsole;
import com.github.tomaslanger.chalk.Chalk;

public class LearningJava {

    static class Phase {

        private String key;
        private String title;
        private long start;

        public Phase(String key, String title) {
            this.key = key;
            this.title = title;
            this.start = System.currentTimeMillis();

            String msg = String.format("*** %s INIT %s", key, title);

            LOG.debug(msg);
            System.out.println(Chalk.on(msg).bold().white());
        }

        public void end() {
            long duration = System.currentTimeMillis() - this.start;

            String msg = String.format(
                    "*** %s DONE in %,d [ms] : %s",
                    this.key, duration, this.title);

            LOG.debug(msg);
            System.out.println(Chalk.on(msg).bold().white());
        }

    }

    private static final Logger LOG = LogManager.getLogger(LearningJava.class);
    private static final long DEFAULT_SEED = 42;
    private static final long CZECHS = 12_000_000;

    private static RandomPersonGenerator rpg;

    private static Phase totalElapsed;
    private static Phase ph;

    private static void phaseSetEncoding() throws UnsupportedEncodingException {
        try {
            String encoding = System.getProperty("file.encoding");
            boolean autoFlush = true;
            LOG.info("Set System.out encoding to " + encoding);
            System.setOut(new PrintStream(System.out, autoFlush, encoding));
        } catch (RuntimeException e) {
            LOG.error("FUCK UP: ", e);  // lolwut
        }
    }

    private static void phaseSystemCheck() {
        Runtime rt = Runtime.getRuntime();
        String msg = String.format(
                "Runtime reports %,d CPU + ( %,d MB FREE / %,d MB MAX / %,d MB TOTAL ) MEMORY",
                rt.availableProcessors(),
                rt.freeMemory() >> 20,
                rt.maxMemory() >> 20,
                rt.totalMemory() >> 20);
        
        LOG.debug(msg);
        System.out.println(msg);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        LOG.debug("Initiating startup sequence ..");
        System.out.println(Chalk.on(
                "SYSTEM BOOT Loading version 701.12 UPG").white().bold());

        ph = new Phase("SETENC", "Set Encoding engine");
        phaseSetEncoding();
        ph.end();

        ph = new Phase("SYSSTS", "System status");
        phaseSystemCheck();
        ph.end();

        ph = new Phase("SYSRDY", "SYSTEM READY");

        totalElapsed = new Phase("TOTELA", "Total Elapsed");
        rpg = new RandomPersonGenerator(new Random(DEFAULT_SEED));
        LOG.debug("Generator: " + rpg);

        ph = new Phase("GEN10R", "Generate 10 randoms");
        for (int i = 0; i < 10; i++) {
            Person p = rpg.getRandomPerson();
            LOG.debug("Got " + p);
            System.out.println(String.format("%d. %s", i + 1, p));
        }
        ph.end();

        ph = new Phase("GENBLK", String.format("Generate %,d randoms in bulk", CZECHS));
        long startMilis = System.currentTimeMillis();
        List<Person> czechs = rpg.getBulk(CZECHS);
        long endMilis = System.currentTimeMillis();
        System.out.println(String.format(
                "Generated %,d entries in %,d miliseconds",
                czechs.size(), (endMilis - startMilis)));
        ph.end();

        ph = new Phase("CALCOL", "Calculate collisions");
        Map<String, Long> map = new HashMap<String, Long>();
        long totalCollisions = 0L;

        for (Person p : czechs) {
            String key = p.toString();
            long timesSeen = map.getOrDefault(key, 0L);

            if (timesSeen > 0) {
                LOG.debug(String.format(
                        "Collision: Person %s already seen %d times.", p, timesSeen));
                totalCollisions += 1;
            }
            map.put(key, timesSeen + 1);
        }
        System.out.println(String.format("Found %,d collisions.", totalCollisions));
        ph.end();

        totalElapsed.end();

    }
}
