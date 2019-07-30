package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class RangeTest {
    private Range range = new Range();

    @Test
    public void whenLinearFunctionsThenLimearResult() {
        List<Double> result = range.diapason(2, 6, x -> x * 3 + 2);
        List<Double> expect = new ArrayList<>(Arrays.asList(8D, 11D, 14D, 17D));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSquaredFunctionsThenLimearResult() {
        List<Double> result = range.diapason(2, 6, x -> x * x + 2);
        List<Double> expect = new ArrayList<>(Arrays.asList(6D, 11D, 18D, 27D));
        assertThat(result, is(expect));
    }

    @Test
    public void whenLogarithmicFunctionsThenLimearResult() {
        List<Double> result = range.diapason(2, 6, Math::log);
        List<Double> expect = new ArrayList<>(Arrays.asList(
                0.6931471805599453D,
                1.0986122886681098D,
                1.3862943611198906D,
                1.6094379124341003D));
        assertThat(result, is(expect));
    }

}
