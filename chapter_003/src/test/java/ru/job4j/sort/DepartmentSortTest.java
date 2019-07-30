package ru.job4j.sort;

import org.junit.Test;
import ru.job4j.sort.ListCompare;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class DepartmentSortTest {

    @Test
    public void whenAscSortThenSuccess() {
        DepartmentSort dep = new DepartmentSort();
        dep.setDepartments(Arrays.asList("K1",
                "K1\\SK1\\SSK2",
                "K2\\SK1",
                "K1\\SK1",
                "K2\\SK1\\SSK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K2",
                "K2\\SK1\\SSK2"));
        dep.ascSort();
        assertThat(dep.getDepartments(), is(new ArrayList<String>(Arrays.asList("K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"))));
    }

    @Test
    public void whenDescSortThenSuccess() {
        DepartmentSort dep = new DepartmentSort();
        dep.setDepartments(Arrays.asList("K1",
                "K1\\SK1\\SSK2",
                "K2\\SK1",
                "K1\\SK1",
                "K2\\SK1\\SSK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K2",
                "K2\\SK1\\SSK2"));
        dep.descSort();
        assertThat(dep.getDepartments(), is(new ArrayList<String>(Arrays.asList("K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2"))));
    }

    @Test
    public void whenAscSortWithNoSoloDepsThenSuccess() {
        DepartmentSort dep = new DepartmentSort();
        dep.setDepartments(Arrays.asList("K1\\SK1\\SSK2",
                "K2\\SK1",
                "K1\\SK1",
                "K2\\SK1\\SSK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K2\\SK1\\SSK2"));
        dep.ascSort();
        assertThat(dep.getDepartments(), is(new ArrayList<String>(Arrays.asList("K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"))));
    }
}
