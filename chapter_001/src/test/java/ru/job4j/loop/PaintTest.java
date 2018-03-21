package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Andrey Sigachev
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String rst = paint.rightTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String rst = paint.leftTrl(4);
        System.out.println(rst);
        assertThat(rst,
            is(
                    new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                            .add("   ^")
                            .add("  ^^")
                            .add(" ^^^")
                            .add("^^^^")
                            .toString()
            )
        );
    }

    @Test
    public void whenPyramid() {
        Paint paint = new Paint();
        String rsl = paint.pyramid(4);
        System.out.println(rsl);
        assertThat(rsl,
             is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^   ")
                                .add("  ^^^  ")
                                .add(" ^^^^^ ")
                                .add("^^^^^^^")
                                .toString()
            )
        );
    }
}


