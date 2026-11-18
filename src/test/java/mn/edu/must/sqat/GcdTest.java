package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * all-DU-pairs coverage for DataFlowTargets.gcd.
 * Line numbers refer to src/main/java/mn/edu/must/sqat/DataFlowTargets.java.
 * DU pair IDs (DU1..DU13) are defined in README.md.
 *
 * DU1  x 13->16      DU2  x 13->20      DU3  x 17->16      DU4  x 17->20
 * DU5  y 13->15 (T)  DU6  y 13->15 (F)  DU7  y 13->16      DU8  y 13->17
 * DU9  y 18->15 (T)  DU10 y 18->15 (F)  DU11 y 18->16      DU12 y 18->17
 * DU13 tmp 16->18
 */
class GcdTest {

    @Test
    @DisplayName("T1 gcd(7,0): zero iterations, covers DU2 x(13->20), DU6 y(13->15 false)")
    void t1_zeroIterations_gcd7_0() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("T2 gcd(12,8): two iterations, covers DU1,DU3,DU4,DU5,DU7,DU8,DU9,DU10,DU11,DU12,DU13")
    void t2_twoIterations_gcd12_8() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("T3 gcd(0,5): one iteration, regression only (no new DU pair)")
    void t3_oneIteration_gcd0_5() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
