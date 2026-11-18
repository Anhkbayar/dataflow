package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for DataFlowTargets.bonus. Line numbers refer to DataFlowTargets.java.
 * DU pair IDs (DU1..DU35) are defined in README.md.
 *
 * Phase 1: ALL-DEFS (AD1, AD2). Phase 2: ALL-DU-PAIRS (T3..T7 added; T8 added in Part C to kill mutant M2).
 */
class BonusTest {

    @Test
    @DisplayName("AD1 {1,1,1,1,1,0,1}, thr 3 -> 8: score def 51 -> use 54, def 54 -> use 56 (+ all other defs)")
    void ad1_streak5ThenBreakThenOne() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 3));
    }

    @Test
    @DisplayName("AD2 {}, thr 1 -> 0: score def 49 -> use 56 (def 49 cannot share a run with def 51)")
    void ad2_emptyArrayThresholdOne() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {}, 1));
    }

    @Test
    @DisplayName("T3 null array -> IAE: DU1 attendance(31->32 T)")
    void t3_nullArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 0));
    }

    @Test
    @DisplayName("T4 {}, thr -1 -> IAE: DU5 threshold(31->32 T)")
    void t4_negativeThresholdThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {}, -1));
    }

    @Test
    @DisplayName("T5 {}, thr 0 -> 5: DU12 present(35->50 T), DU34 score(51->56)")
    void t5_emptyArrayThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("T6 {1,1,1,1,1}, thr 6 -> 3: DU16 present(40->50 F), DU31 score(49->54)")
    void t6_streak5BelowThreshold() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("T7 {1,0,1}, thr 1 -> 5: DU30 best(43->53 F), DU34 score(51->56)")
    void t7_singleBreakThenOne() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 0, 1}, 1));
    }

    @Test
    @DisplayName("T8 {1,0,1,1,1,1}, thr 1 -> 5: kills M2 (best = present); present > streak when best is updated")
    void t8_streak4AfterBreakStaysBelow5() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 0, 1, 1, 1, 1}, 1));
    }
}
