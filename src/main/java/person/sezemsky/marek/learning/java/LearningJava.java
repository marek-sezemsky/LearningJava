package person.sezemsky.marek.learning.java;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.tomaslanger.chalk.Chalk;
import java.io.IOException;
import java.util.ArrayList;

public class LearningJava {

    private static final Logger LOG = LogManager.getLogger(LearningJava.class);
    private static final long DEFAULT_SEED = 42;
    private static final long BULK_COUNT = 500_000;
    private static final String ENCODING_DEFAULT = "UTF-8";

    static class Phase {

        private final String key;
        private final String title;
        private final long start;

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
            System.out.println(Chalk.on(msg).bold().green());
            System.out.println();
        }

    }

    private static void phaseSystemCheck() {
        Phase ph = new Phase("SYSSTS", "System status");

        Runtime rt = Runtime.getRuntime();
        String msg = String.format(
                "Runtime reports %,d CPU + ( %,d MB FREE / %,d MB MAX / %,d MB TOTAL ) MEMORY",
                rt.availableProcessors(),
                rt.freeMemory() >> 20,
                rt.maxMemory() >> 20,
                rt.totalMemory() >> 20);

        LOG.debug(msg);
        System.out.println(msg);

        ph.end();
    }

    private static List<Person> phaseGen10(RandomPersonGenerator gen) {
        Phase ph = new Phase("GEN10R", "Generate 10 random persons");
        List<Person> ret = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person p = gen.getRandomPerson();
            ret.add(p);
            LOG.debug("Got " + p);
            System.out.println(String.format("%d. %s", i + 1, p));
        }
        ph.end();
        
        return ret;
    }

    private static List<Person> phaseGetBulkList(RandomPersonGenerator gen, long count) {
        Phase ph = new Phase("GENBLK", String.format("Generate %,d randoms in bulk", count));
        List<Person> ret = gen.getBulk(count);
        ph.end();

        return ret;
    }

    private static void phaseCalculateCollisions(List<Person> list) {
        Phase ph = new Phase("CALCOL", "Calculate 'toString' collisions");
        Map<String, Long> map = new HashMap<>();

        long totalCollisions = 0L;

        for (Person p : list) {
            // let's assume Person.toString retuns what should be ideally a uniqe identificator
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
    }

    private static void phaseWriteCSV(List<Person> list, String filePath) throws IOException {

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        RandomPersonGenerator rpg;
        List<Person> people;

        try {
            String encoding = System.getProperty("file.encoding", ENCODING_DEFAULT);
            boolean autoFlush = true;
            LOG.info("Set System.out encoding to " + encoding);
            System.setOut(new PrintStream(System.out, autoFlush, encoding));
            Chalk.setColorEnabled(true);
        } catch (RuntimeException e) {
            LOG.error("FUCK UP: ", e);  // lolwut
        }

        LOG.debug(
                "Initiating startup sequence ..");
        System.out.println(Chalk.on(
                "SYSTEM BOOT Loading version 701.12 UPG").blue().bold());

        phaseSystemCheck();

        rpg = new RandomPersonGenerator(new Random(DEFAULT_SEED));
        LOG.debug("Generator: " + rpg);

        /* Generate 10 randoms */
        people = phaseGen10(rpg);

        /* Generate big bulk of randoms */
        people = phaseGetBulkList(rpg, BULK_COUNT);

        /* Calculate collisions in bulk generated randoms */
        phaseCalculateCollisions(people);

        /* Save bulk generated entries */
        try {
            phaseWriteCSV(people, "filename.csv");
        } catch (IOException e) {
            LOG.error("Failed to write CSV file", e);
        }
    }
}
