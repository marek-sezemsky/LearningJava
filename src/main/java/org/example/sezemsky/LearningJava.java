package org.example.sezemsky;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.tomaslanger.chalk.Chalk;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;

public class LearningJava {

    private static final Logger LOG = LogManager.getLogger(LearningJava.class);
    private static final long DEFAULT_SEED = 42;
    private static final long BULK_COUNT = 500_000;
    private static final String ENCODING_DEFAULT = "UTF-8";

    static class Stage {

        private final String key;
        private final String title;
        private final long start;

        public Stage(String key, String title) {
            this.key = key;
            this.title = title;
            this.start = System.currentTimeMillis();

            String msg = String.format(
                    ">>> %s INIT %s",
                    this.key, this.title);

            LOG.debug(msg);
            System.out.println();
            System.out.println(Chalk.on(msg).yellow());
        }

        public void info(String msg) {
            LOG.info(msg);
            System.out.println(Chalk.on(msg).white());
        }

        public void error(String msg, Throwable t) {
            LOG.error(msg, t);  // lolwut
            System.out.println(Chalk.on("!X! FUCKUP --- " + t).red());
        }

        public void end() {
            long duration = System.currentTimeMillis() - this.start;

            String msg = String.format(
                    "<<< %s DONE in %,d [ms] : %s",
                    this.key, duration, this.title);

            LOG.debug(msg);
            System.out.println(Chalk.on(msg).yellow());
            System.out.println();
        }

    }

    private static void phaseSystemInit() {
        Stage s = new Stage("BOOTUP", "System bootup sequence");

        try {
            Chalk.setColorEnabled(true);
            String encoding = System.getProperty("file.encoding", ENCODING_DEFAULT);
            boolean autoFlush = true;
            s.info("Set System.out encoding to " + encoding);
            System.setOut(new PrintStream(System.out, autoFlush, encoding));
        } catch (RuntimeException | UnsupportedEncodingException e) {
            s.error("LOLWUT?!", e);
        }

        s.end();
    }

    private static void phaseSystemCheck() {
        Stage s = new Stage("SYSSTS", "System status");

        Runtime rt = Runtime.getRuntime();
        String msg = String.format(
                "Runtime reports %,d CPU + ( %,d MB FREE / %,d MB MAX / %,d MB TOTAL ) MEMORY",
                rt.availableProcessors(),
                rt.freeMemory() >> 20,
                rt.maxMemory() >> 20,
                rt.totalMemory() >> 20);

        LOG.debug(msg);
        System.out.println(msg);

        s.end();
    }

    private static List<Person> phaseGen10(RandomPersonGenerator gen) {
        Stage s = new Stage("GEN10R", "Generate 10 random persons");
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person p = gen.getRandomPerson();
            list.add(p);
            s.info("Created: " + p);
        }

        s.end();
        return list;
    }

    private static List<Person> phaseGetBulkList(RandomPersonGenerator gen, long count) {
        Stage s = new Stage("GENBLK", String.format("Generate %,d randoms in bulk", count));

        List<Person> list = gen.getBulk(count);

        s.end();
        return list;
    }

    private static long phaseCalculateCollisions(List<Person> list) {
        Stage s = new Stage("CALCOL", "Calculate 'toString' collisions");
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
        s.info(String.format("Found %,d collisions.", totalCollisions));

        s.end();
        return totalCollisions;
    }

    private static void phaseWriteText(List<Person> list, String fileName) {
        Stage s = new Stage("WRTTXT", "Write records into text file");

        // TODO add some StopWatch ticker to show progress
        try (FileWriter fw = new FileWriter(fileName)) {
            s.info("Writing text into : " + fileName);
            // TODO add some StopWatch ticker to show progress
            for (Person p : list) {
                fw.write(p.toString());
                fw.write(System.lineSeparator());
            }
            s.info("Done writing.");
        } catch (IOException e) {
            LOG.error("Failed to write TXT file", e);
        }

        s.end();
    }

    private static void phaseWriteJSON(List<Person> people, String fileName) {
        Stage s = new Stage("WRTJSN", "Write records into JSON file");

        // TODO add some StopWatch ticker to show progress
        try (FileWriter fw = new FileWriter(fileName)) {
            s.info("Writing JSON into : " + fileName);
            fw.write("{");  // hey.....
            fw.write(System.lineSeparator());
            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                JsonObject jo = Json.createObjectBuilder()
                        .add("firstName", p.getFirstName())
                        .add("lastName", p.getLastName())
                        .add("birthDate", p.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                        .build();
                fw.write(jo.toString());
                if (i < people.size() - 1) {
                    fw.write(","); // ...psst....
                    fw.write(System.lineSeparator());
                }
            }
            fw.write("}"); // ...wanna do something nasty with JSON?
            fw.write(System.lineSeparator());
            s.info("Done writing.");
        } catch (IOException e) {
            LOG.error("Failed to write JSON file", e);
        }

        s.end();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        RandomPersonGenerator rpg;
        List<Person> people;

        phaseSystemInit();
        phaseSystemCheck();

        rpg = new RandomPersonGenerator(new Random(DEFAULT_SEED));
        LOG.debug("Generator: " + rpg);

        /* Generate 10 randoms */
        phaseGen10(rpg);

        /* Generate big bulk of randoms */
        people = phaseGetBulkList(rpg, BULK_COUNT);

        /* Calculate collisions in bulk generated randoms */
        phaseCalculateCollisions(people);

        /* Save bulk generated entries */
        phaseWriteText(people, Paths.get("./target/filename.txt")
                .toAbsolutePath().normalize().toString());

        phaseWriteJSON(people, Paths.get("./target/filename.json")
                .toAbsolutePath().normalize().toString());

    }
}
