package person.sezemsky.marek.learning.java;

import com.github.tomaslanger.chalk.Chalk;
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

public class LearningJava {

    private static final Logger LOG = LogManager.getLogger(LearningJava.class);

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final long DEFAULT_SEED = 42;

    private static final long CZECHS = 10_000;  // 10_000_000

    private static RandomPersonGenerator rpg;

    public LearningJava() {
    }

    private static void header(String msg) {
        LOG.info(msg);
        System.out.println("\n\n##-- " + msg);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        LOG.debug("Initiating startup sequence ..");
        System.out.println(Chalk.on(
                "\nBOOTUP Loading version 701.12 UPG").white().bold());

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
        long free = rt.freeMemory() >> 20;
        long max = rt.maxMemory() >> 20;
        long total = rt.totalMemory() >> 20;
        System.out.println(String.format(
                "Runtime reports %,d CPU + ( %,d MB FREE / %,d MB MAX / %,d MB TOTAL ) memory",
                cpus, free, max, total));

        LOG.debug("System ready");
        System.out.println(Chalk.on(
                "SYSCHK DONE\nSYSTEM READY").green().bold());

        header("Obtain generator");
        rpg = new RandomPersonGenerator(new Random(DEFAULT_SEED));
        LOG.debug("Generator: " + rpg);

        header("Generate 10 randoms");
        for (int i = 0; i < 10; i++) {
            Person p = rpg.getRandomPerson();
            LOG.debug("Got " + p);
            System.out.println(String.format("%d %s", i, p));
        }

        header("Generate bulk randoms");
        long startMilis = System.currentTimeMillis();
        List<Person> czechs = rpg.getBulk(CZECHS);
        long endMilis = System.currentTimeMillis();
        System.out.println(String.format(
                "Generated %,d entries in %,d miliseconds",
                czechs.size(), (endMilis - startMilis)));

        header("Calculating collisions");
        Map<String, Long> map = new HashMap<String, Long>();
        long totalCollisions = 0L;

        for (Person p : czechs) {
            String key = p.toString();
            long timesSeen = map.getOrDefault(key, 0L);

            if (timesSeen > 0) {
                System.out.println(String.format(
                        "Collision: Person %s already seen %d times.", p, timesSeen));
                totalCollisions += 1;
            }
            map.put(key, timesSeen + 1);
        }
        System.out.println("Found " + totalCollisions + " collisions.")

        header("DONE");

    }
}
