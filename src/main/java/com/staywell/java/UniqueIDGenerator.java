package com.staywell.java;

import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import de.mkammerer.snowflakeid.options.Options;
import de.mkammerer.snowflakeid.structure.Structure;
import de.mkammerer.snowflakeid.time.MonotonicTimeSource;
import de.mkammerer.snowflakeid.time.TimeSource;

import java.time.Instant;

/**
 * todo: to generate snowflake like unique ids twitter
 *
 * Such generated id in binary looks like this (this is 4425020822061056 in decimal):
 *
 * 0000000000001111101110001000100001110010001110010000000000000000
 * ||                                            | |
 * || Timestamp (16880114830)                    | | Sequence (0)
 * |                                             |
 * | Sign bit (always 0)                         | Generator id (1)
 *
 * The structure used for this is 45 bits for the timestamp, 2 for the generator and
 * the remaining 16 for the sequence. This structure can be changed easily, see below for the code.
 *
 * // add maven depenencies :
 * The default settings for the Structure are 41 bits for the timestamp,
 * 10 for the generator id and 12 for the sequence.
  */

public class UniqueIDGenerator {

    public static void main(String[] args) {

        TimeSource timeSource = new MonotonicTimeSource(Instant.parse("2020-04-01T00:00:00Z"));

        Structure structure = new Structure(45, 2, 16);
        System.out.println("Max generators: " + structure.maxGenerators());
        System.out.println("Max sequences per ms per generator: " + structure.maxSequenceIds());
        System.out.println("Max sequences per ms total: " + structure.maxSequenceIds() * structure.maxGenerators());
        System.out.println("Wraparound duration: " + structure.calculateWraparoundDuration(timeSource));
        System.out.println("Wraparound date: " + structure.calculateWraparoundDate(timeSource));

        generateSnowflakeUID();
    }
    private static void generateSnowflakeUID() {

        // Use a custom epoch
        TimeSource timeSource = new MonotonicTimeSource(Instant.parse("2020-04-01T00:00:00Z"));
        // Use 45 bits for the timestamp, 2 bits for the generator and 16 bits for the sequence
        Structure structure = new Structure(45, 2, 16);
        // If the sequence number overflows, throw an exception
        Options options = new Options(Options.SequenceOverflowStrategy.THROW_EXCEPTION);


        // generatorId must be unique over all your instances!
        int generatorId = 0;

        // use default generator settings
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.createDefault(generatorId);

        // generate 10 ids
        for (int i = 0; i < 10; i++) {
            long id = generator.next();
            System.out.println(id);
        }
    }
}
