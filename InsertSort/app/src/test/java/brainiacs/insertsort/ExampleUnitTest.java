package brainiacs.insertsort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void insertionSortTest1() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {1,2,3,4,5,6,9,8};
        assertEquals("12345698\n" +
                "12345698\n" +
                "12345698\n" +
                "12345698\n" +
                "12345698\n" +
                "12345698\n" +
                "12345698\n" +
                "12345689",m.insertionSort(nums));
    }

    @Test
    public void insertionSortTest2() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {3,2,1};
        assertEquals("321\n" +
                "231\n" +
                "123",m.insertionSort(nums));

    }
    @Test
    public void insertionSortTest3() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {4,3,6,2,6,2,6,8};
        assertEquals("43626268\n" +
                "34626268\n" +
                "34626268\n" +
                "23466268\n" +
                "23466268\n" +
                "22346668\n" +
                "22346668\n" +
                "22346668",m.insertionSort(nums));

    }

    @Test
    public void greaterThan9() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {30,20,10};
        assertEquals("wrong entry",m.insertionSort(nums));
    }
    @Test
    public void sort1Num() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {4};
        assertEquals("wrong entry",m.insertionSort(nums));
    }
    @Test
    public void exceedMaxSize() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {4,3,5,2,5,3,5,7,3};
        assertEquals("wrong entry",m.insertionSort(nums));
    }
    @Test
    public void emptyList() throws Exception {
        MainActivity m = new MainActivity();
        int[] nums = {};
        assertEquals("wrong entry",m.insertionSort(nums));
    }
}