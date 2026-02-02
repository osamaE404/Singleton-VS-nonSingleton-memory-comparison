package sa.edu.kau.fcit.cpit252;

import org.junit.Test;
import sa.edu.kau.fcit.cpit252.logging.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class Lab2Test {

    // Should restrict the instantiation  of the Logger class to a singular instance.
    @Test
    public void ShouldBeSingleton() {
        Logger log1 = Logger.getLoggerInstance();
        Logger log2 = Logger.getLoggerInstance();
        assertEquals(log1, log2);
    }

    // We should not have access to the constructor as it should be private
    @Test
    public void ShouldBePrivateConstructor() {
        for (Constructor c : Logger.class.getConstructors()) {
            assertTrue(Modifier.isPrivate(c.getModifiers()));
        }
    }

    // We should have a private and static global variable that holds a reference to the singleton instance.
    @Test
    public void shouldBeStatic(){
        boolean hasLoggerVar = false;
        for (Field f : Logger.class.getDeclaredFields()){
            if(f.getType() == Logger.class){
                hasLoggerVar = true;
                assertTrue(Modifier.isPrivate(f.getModifiers()));
                assertTrue(Modifier.isStatic(f.getModifiers()));
            }
        }
        assertTrue(hasLoggerVar);
    }

}