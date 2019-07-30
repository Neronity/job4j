package ru.job4j.sort;

import java.util.*;

public class DepartmentSort {
    private List<String> departments = new ArrayList<>();
    private List<DepartmentGroup> depGroups = new ArrayList<>();

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    private void divideByGroups() {
        Collections.sort(departments);
        String currentMainDep = "";
        DepartmentGroup currentGroup = null;
        for (String s : departments) {
            String[] depPath = s.split("\\\\");
            String mainDep = depPath[0];
            if (!mainDep.equals(currentMainDep)) {
                if (currentGroup != null) {
                    depGroups.add(currentGroup);
                }
                currentGroup = new DepartmentGroup(new PriorityQueue<>());
                currentMainDep = mainDep;
                if (depPath.length > 1) {
                    currentGroup.getDeps().add(mainDep);
                }
            }
            currentGroup.getDeps().add(s);
        }
        depGroups.add(currentGroup);
    }


    public void ascSort() {
        divideByGroups();
        groupsToList();
    }

    public void descSort() {
        divideByGroups();
        Collections.reverse(depGroups);
        groupsToList();
    }

    private void groupsToList() {
        List<String> result = new ArrayList<>();
        for (DepartmentGroup dg : depGroups) {
            result.addAll(dg.getDeps());
        }
        departments = result;
    }
}
