package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapEntryValueStorageTest.class,
        MapUuidStorageTest.class,
        SortedArrayStorageTest.class
})
public class AllStorageTest {

}
